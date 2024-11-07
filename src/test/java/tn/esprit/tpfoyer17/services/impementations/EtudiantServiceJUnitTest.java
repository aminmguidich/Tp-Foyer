package tn.esprit.tpfoyer17.services.impementations;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Etudiant;
import tn.esprit.tpfoyer17.repositories.EtudiantRepository;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EtudiantServiceJUnitTest {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Test
    @Order(1)
    void addEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEtudiant("Alice");
        etudiant.setPrenomEtudiant("Smith");
        etudiant.setCinEtudiant(654321);
        etudiant.setDateNaissance(new Date());

        Etudiant savedEtudiant = etudiantService.addEtudiants(etudiant);
        assertNotNull(savedEtudiant);
        assertEquals("Alice", savedEtudiant.getNomEtudiant());
        System.out.println("Add Etudiant: Ok");
    }


    @Test
    @Order(2)
    void retrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();
        assertNotNull(etudiants);
        assertFalse(etudiants.isEmpty());
        System.out.println("Retrieve All Etudiants: Ok");
    }

    @Test
    @Order(3)
    void retrieveEtudiant() {
        Etudiant etudiant = etudiantService.retrieveEtudiant(1L); // Adjust ID as necessary
        assertNotNull(etudiant);
        assertEquals("Alice", etudiant.getNomEtudiant()); // Adjust based on your data
        System.out.println("Retrieve Etudiant: Ok");
    }

    @Test
    @Order(4)
    void removeEtudiant() {
        etudiantService.removeEtudiant(1L); // Adjust ID as necessary
        assertThrows(EntityNotFoundException.class, () -> etudiantService.retrieveEtudiant(1L));
        System.out.println("Remove Etudiant: Ok");
    }

    @Test
    @Order(6)
    void modifyEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCinEtudiant(1L); // Adjust based on your data
        etudiant.setNomEtudiant("Updated Name");

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);
        assertNotNull(updatedEtudiant);
        assertEquals("Updated Name", updatedEtudiant.getNomEtudiant());
        System.out.println("Modify Etudiant: Ok");
    }
    @Test
    @Order(5) // Adjust the order as needed
    void findByReservationsAnneeUniversitaire() {
        // Assuming some Etudiants with reservations for the current academic year are already in the database.

        // Act
        List<Etudiant> etudiants = etudiantService.findByReservationsAnneeUniversitaire();

        // Assert
        assertNotNull(etudiants);
        assertTrue(etudiants.isEmpty(), "Expected non-empty list of Etudiants with reservations");
        System.out.println("Find By Reservations Annee Universitaire: Ok");
    }



}
