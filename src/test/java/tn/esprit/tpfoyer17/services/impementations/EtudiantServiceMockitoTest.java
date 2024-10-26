package tn.esprit.tpfoyer17.services.impementations;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EtudiantServiceMockitoTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("youssef");
        etudiant.setPrenomEtudiant("ghrir");
        etudiant.setCinEtudiant(123456);
        Date dateNaissance = new Date();
        etudiant.setDateNaissance(dateNaissance);

        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant savedEtudiant = etudiantService.addEtudiants(etudiant);

        // Assert
        assertNotNull(savedEtudiant);
        assertEquals("youssef", savedEtudiant.getNomEtudiant());
        assertEquals("ghrir", savedEtudiant.getPrenomEtudiant());
        assertEquals(123456, savedEtudiant.getCinEtudiant());
        assertEquals(dateNaissance, savedEtudiant.getDateNaissance()); // Vérification de la date de naissance

        verify(etudiantRepository, times(1)).save(etudiant);
    }


    @Test
    void retrieveAllEtudiants() {
        // Arrange
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant());
        etudiants.add(new Etudiant());
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        // Act
        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();

        // Assert
        assertNotNull(retrievedEtudiants);
        assertEquals(2, retrievedEtudiants.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void retrieveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1L);

        // Assert
        assertNotNull(retrievedEtudiant);
        assertEquals(1L, retrievedEtudiant.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void retrieveEtudiant_NotFound() {
        // Arrange
        when(etudiantRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> etudiantService.retrieveEtudiant(1L));
    }

    @Test
    void modifyEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant();
        etudiant.setCinEtudiant(1L);
        etudiant.setNomEtudiant("Jane");
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        // Act
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        // Assert
        assertNotNull(updatedEtudiant);
        assertEquals("Jane", updatedEtudiant.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void removeEtudiant() {
        // Act
        etudiantService.removeEtudiant(1L);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(1L);
    }
    @Test
    void findByReservationsAnneeUniversitaire() {
        // Arrange
        List<Etudiant> etudiants = new ArrayList<>();
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        etudiants.add(etudiant1);
        etudiants.add(etudiant2);

        when(etudiantRepository.findByReservationsAnneeUniversitaire(any(LocalDate.class))).thenReturn(etudiants);

        // Act
        List<Etudiant> result = etudiantService.findByReservationsAnneeUniversitaire();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findByReservationsAnneeUniversitaire(any(LocalDate.class));
    }

}
