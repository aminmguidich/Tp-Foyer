package tn.esprit.tpfoyer17.services.impementations;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.repositories.ReservationRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.interfaces.IReservationService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationService implements IReservationService {
    ReservationRepository reservationRepository;
    EtudiantRepository etudiantRepository;
    ChambreRepository chambreRepository;
    UniversiteRepository universiteRepository;

    @Override
    public List<Reservation> retrieveAllReservation() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);

    }

    @Transactional
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCinEtudiant(cinEtudiant);
        Set<Reservation> reservationList = etudiant.getReservations();
        for (Reservation reservation : reservationList) {
            reservation.getEtudiants().remove(etudiant);
            reservationRepository.save(reservation);
            Chambre chambre = chambreRepository.findByReservationsIdReservation(reservation.getIdReservation());
            chambre.getReservations().remove(reservation);
            switch (chambre.getTypeChambre()) {
                case SIMPLE -> reservation.setEstValide(true);
                case DOUBLE -> {
                    if (reservation.getEtudiants().size() == 2) reservation.setEstValide(true);
                }
                case TRIPLE -> {
                    if (reservation.getEtudiants().size() == 3) reservation.setEstValide(true);
                }
            }

        }
        return null;
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(LocalDate anneeUniversite, String nomUniversite) {
        return reservationRepository.recupererParBlocEtTypeChambre(nomUniversite, anneeUniversite);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversiteKeyWord(LocalDate anneeUniversite, String nomUniversite) {
        return universiteRepository.findByFoyerBlocsChambresReservationsAnneeUniversitaireAndNomUniversite(anneeUniversite, nomUniversite);
    }

    @Transactional
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCinEtudiant(cinEtudiant);
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);

        assert chambre != null;
        String numReservation = generateId(chambre.getNumeroChambre(),
                chambre.getBloc().getNomBloc());

        Reservation reservation = reservationRepository.findById(numReservation).orElse(
                Reservation.builder()
                        .idReservation(numReservation)
                        .etudiants(new HashSet<>())
                        .anneeUniversitaire(LocalDate.now())
                        .estValide(true)
                        .build());


        //Vérifier capacité maximale de la chambre
        if (reservation.isEstValide() && (capaciteChambreMaximale(chambre))) {
            chambre.getReservations().add(reservation);
            reservation.getEtudiants().add(etudiant);
            reservationRepository.save(reservation);
        }

        switch (chambre.getTypeChambre()) {
            case SIMPLE -> reservation.setEstValide(false);
            case DOUBLE -> {
                if (reservation.getEtudiants().size() == 2) reservation.setEstValide(false);
            }
            case TRIPLE -> {
                if (reservation.getEtudiants().size() == 3) reservation.setEstValide(false);
            }
        }
        return reservationRepository.save(reservation);

    }


    private String generateId(long numChambre, String nomBloc) {
        return numChambre + "-" + nomBloc + "-" + LocalDate.now().getYear();
    }

    private boolean capaciteChambreMaximale(Chambre chambre) {
        switch (chambre.getTypeChambre()) {
            case SIMPLE -> {
                return chambre.getReservations().size() < 2;
            }
            case DOUBLE -> {
                return chambre.getReservations().size() < 3;
            }
            case TRIPLE -> {
                return chambre.getReservations().size() < 4;
            }
            default -> {
                return false;
            }
        }
    }
}
