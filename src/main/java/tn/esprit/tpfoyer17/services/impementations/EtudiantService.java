package tn.esprit.tpfoyer17.services.impementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.services.interfaces.IEtudiantService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EtudiantService implements IEtudiantService {
    EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        log.info("Retrieving all students");
        return (List<Etudiant>) etudiantRepository.findAll();
    }





    @Override
    public Etudiant addEtudiants(Etudiant etudiant) {
        log.info("Adding a single student: {}", etudiant);
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        log.info("Updating student: {}", e);
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant) {
        log.info("Retrieving student with ID: {}", idEtudiant);
        return etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new EntityNotFoundException("Etudiant not found with ID: " + idEtudiant));
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        log.info("Removing student with ID: {}", idEtudiant);
        etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public List<Etudiant> findByReservationsAnneeUniversitaire() {
        log.info("Finding students by reservations for the current academic year");
        return etudiantRepository.findByReservationsAnneeUniversitaire(LocalDate.now());
    }
}
