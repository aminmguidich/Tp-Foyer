package tn.esprit.tpfoyer17;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.repositories.ReservationRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.ReservationService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    EtudiantRepository etudiantRepository;

    @Mock
    ChambreRepository chambreRepository;

    @Mock
    UniversiteRepository universiteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReservation() {
        // Arrange
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservationList);

        // Act
        List<Reservation> result = reservationService.retrieveAllReservation();

        // Assert
        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testUpdateReservation() {
        // Arrange
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Act
        Reservation updatedReservation = reservationService.updateReservation(reservation);

        // Assert
        assertNotNull(updatedReservation);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testRetrieveReservation() {
        // Arrange
        String id = "R1";
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.retrieveReservation(id);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(id);
    }

    @Test
    void testAnnulerReservation() {
        // Arrange
        long cinEtudiant = 12345678;
        Etudiant etudiant = new Etudiant();
        etudiant.setReservations(new HashSet<>());
        Reservation reservation = new Reservation();
        reservation.setEstValide(true);
        etudiant.getReservations().add(reservation);

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(etudiant);

        // Act
        Reservation result = reservationService.annulerReservation(cinEtudiant);

        // Assert
        assertNull(result);  // Assuming `annulerReservation` returns null in this case
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testGetReservationParAnneeUniversitaireEtNomUniversite() {
        // Arrange
        LocalDate anneeUniversitaire = LocalDate.of(2023, 1, 1);
        String nomUniversite = "University1";
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation());
        when(reservationRepository.recupererParBlocEtTypeChambre(nomUniversite, anneeUniversitaire)).thenReturn(reservationList);

        // Act
        List<Reservation> result = reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversitaire, nomUniversite);

        // Assert
        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).recupererParBlocEtTypeChambre(nomUniversite, anneeUniversitaire);
    }
}

