package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;
import tn.esprit.tpfoyer17.repositories.ReservationRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.ReservationService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceMockitoTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Etudiant etudiant;
    private Chambre chambre;
    private Bloc bloc;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize Etudiant
        etudiant = Etudiant.builder()
                .cinEtudiant(12345678L)
                .nomEtudiant("Doe")
                .prenomEtudiant("John")
                .reservations(new HashSet<>())
                .build();

        // Initialize Bloc
        bloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A")
                .capaciteBloc(100)
                .chambres(new HashSet<>())
                .build();

        // Initialize Chambre
        chambre = Chambre.builder()
                .idChambre(1L)
                .numeroChambre(101)
                .typeChambre(TypeChambre.DOUBLE)
                .bloc(bloc)
                .reservations(new HashSet<>())
                .build();

        // Add chambre to bloc
        bloc.getChambres().add(chambre);

        // Initialize Reservation
        reservation = Reservation.builder()
                .idReservation("101-Bloc A-2024")
                .anneeUniversitaire(LocalDate.of(2024, 1, 1))
                .estValide(true)
                .etudiants(new HashSet<>(Collections.singletonList(etudiant)))
                .build();

        // Add reservation to chambre and etudiant
        chambre.getReservations().add(reservation);
        etudiant.getReservations().add(reservation);
    }

    @Test
    void testRetrieveAllReservation() {
        // Arrange
        List<Reservation> reservations = Arrays.asList(reservation);
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.retrieveAllReservation();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testUpdateReservation() {
        // Arrange
        Reservation updatedReservation = Reservation.builder()
                .idReservation("101-Bloc A-2024")
                .anneeUniversitaire(LocalDate.of(2024, 1, 1))
                .estValide(false)
                .etudiants(new HashSet<>(Collections.singletonList(etudiant)))
                .build();

        when(reservationRepository.save(updatedReservation)).thenReturn(updatedReservation);

        // Act
        Reservation result = reservationService.updateReservation(updatedReservation);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEstValide());
        verify(reservationRepository, times(1)).save(updatedReservation);
    }

    @Test
    void testRetrieveReservation_Found() {
        // Arrange
        String idReservation = "101-Bloc A-2024";
        when(reservationRepository.findById(idReservation)).thenReturn(Optional.of(reservation));

        // Act
        Reservation result = reservationService.retrieveReservation(idReservation);

        // Assert
        assertNotNull(result);
        assertEquals(idReservation, result.getIdReservation());
        verify(reservationRepository, times(1)).findById(idReservation);
    }

    @Test
    void testRetrieveReservation_NotFound() {
        // Arrange
        String idReservation = "999-Bloc Z-2024";
        when(reservationRepository.findById(idReservation)).thenReturn(Optional.empty());

        // Act
        Reservation result = reservationService.retrieveReservation(idReservation);

        // Assert
        assertNull(result);
        verify(reservationRepository, times(1)).findById(idReservation);
    }

    @Test
    void testAjouterReservation_Success() {
        // Arrange
        long idChambre = chambre.getIdChambre();
        long cinEtudiant = etudiant.getCinEtudiant();

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(etudiant);
        when(chambreRepository.findById(idChambre)).thenReturn(Optional.of(chambre));
        when(reservationRepository.findById(anyString())).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation result = reservationService.ajouterReservation(idChambre, cinEtudiant);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEstValide()); // Based on the initial setup
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verify(chambreRepository, times(1)).findById(idChambre);
        verify(reservationRepository, times(2)).save(any(Reservation.class)); // One for initial add, one for switch
    }

    @Test
    void testAjouterReservation_EtudiantNotFound() {
        // Arrange
        long idChambre = chambre.getIdChambre();
        long cinEtudiant = 99999999L;

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.ajouterReservation(idChambre, cinEtudiant);
        });

        assertEquals("No student found with CIN: " + cinEtudiant, exception.getMessage());
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verifyNoMoreInteractions(chambreRepository);
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    void testAjouterReservation_ChambreNotFound() {
        // Arrange
        long idChambre = 999L;
        long cinEtudiant = etudiant.getCinEtudiant();

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(etudiant);
        when(chambreRepository.findById(idChambre)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.ajouterReservation(idChambre, cinEtudiant);
        });

        assertEquals("No room found with ID: " + idChambre, exception.getMessage());
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verify(chambreRepository, times(1)).findById(idChambre);
        verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    void testAnnulerReservation_Success() {
        // Arrange
        long cinEtudiant = etudiant.getCinEtudiant();

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(etudiant);
        when(chambreRepository.findByReservationsIdReservation(reservation.getIdReservation())).thenReturn(chambre);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation result = reservationService.annulerReservation(cinEtudiant);

        // Assert
        assertNull(result); // As per implementation, it returns null after cancellation
        assertFalse(reservation.getEtudiants().contains(etudiant));
        assertFalse(chambre.getReservations().contains(reservation));
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verify(reservationRepository, times(1)).save(reservation);
        verify(chambreRepository, times(1)).findByReservationsIdReservation(reservation.getIdReservation());
    }

    @Test
    void testAnnulerReservation_EtudiantNotFound() {
        // Arrange
        long cinEtudiant = 99999999L;

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(null);

        // Act
        Reservation result = reservationService.annulerReservation(cinEtudiant);

        // Assert
        assertNull(result);
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verifyNoMoreInteractions(reservationRepository);
        verifyNoMoreInteractions(chambreRepository);
    }

    @Test
    void testAnnulerReservation_NoReservations() {
        // Arrange
        long cinEtudiant = etudiant.getCinEtudiant();
        etudiant.setReservations(new HashSet<>());

        when(etudiantRepository.findByCinEtudiant(cinEtudiant)).thenReturn(etudiant);

        // Act
        Reservation result = reservationService.annulerReservation(cinEtudiant);

        // Assert
        assertNull(result);
        verify(etudiantRepository, times(1)).findByCinEtudiant(cinEtudiant);
        verifyNoMoreInteractions(reservationRepository);
        verifyNoMoreInteractions(chambreRepository);
    }



    @Test
    void testRetrieveAllReservation_Empty() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Reservation> result = reservationService.retrieveAllReservation();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testGetReservationParAnneeUniversitaireEtNomUniversite() {
        // Arrange
        LocalDate anneeUniversitaire = LocalDate.of(2024, 1, 1);
        String nomUniversite = "ESPRIT";
        List<Reservation> reservations = Arrays.asList(reservation);

        when(reservationRepository.recupererParBlocEtTypeChambre(nomUniversite, anneeUniversitaire)).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversitaire, nomUniversite);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).recupererParBlocEtTypeChambre(nomUniversite, anneeUniversitaire);
    }

    @Test
    void testGetReservationParAnneeUniversitaireEtNomUniversiteKeyWord() {
        // Arrange
        LocalDate anneeUniversitaire = LocalDate.of(2024, 1, 1);
        String nomUniversite = "ESPRIT";
        List<Reservation> reservations = Arrays.asList(reservation);

        when(universiteRepository.findByFoyerBlocsChambresReservationsAnneeUniversitaireAndNomUniversite(anneeUniversitaire, nomUniversite))
                .thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.getReservationParAnneeUniversitaireEtNomUniversiteKeyWord(anneeUniversitaire, nomUniversite);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(universiteRepository, times(1)).findByFoyerBlocsChambresReservationsAnneeUniversitaireAndNomUniversite(anneeUniversitaire, nomUniversite);
    }

    @Test
    void testCapaciteChambreMaximale_Simple() {
        // Arrange
        Chambre simpleChambre = Chambre.builder()
                .idChambre(2L)
                .numeroChambre(102)
                .typeChambre(TypeChambre.SIMPLE)
                .bloc(bloc)
                .reservations(new HashSet<>())
                .build();

        // Act
        boolean result = reservationService.capaciteChambreMaximale(simpleChambre);

        // Assert
        assertTrue(result, "SIMPLE chambre should allow up to 2 reservations");
    }

    @Test
    void testCapaciteChambreMaximale_Double() {
        // Arrange
        Chambre doubleChambre = Chambre.builder()
                .idChambre(3L)
                .numeroChambre(103)
                .typeChambre(TypeChambre.DOUBLE)
                .bloc(bloc)
                .reservations(new HashSet<>(Arrays.asList(
                        Reservation.builder().idReservation("103-Bloc A-2024").build(),
                        Reservation.builder().idReservation("103-Bloc A-2024-2").build()
                )))
                .build();

        // Act
        boolean result = reservationService.capaciteChambreMaximale(doubleChambre);

        // Assert
        assertTrue(result, "DOUBLE chambre should allow up to 3 reservations");
    }


}
