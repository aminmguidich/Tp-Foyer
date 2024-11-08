package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.services.impementations.UniversiteService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
@SpringBootTest
 class JunitTest {
    @Autowired
    private UniversiteService universiteService;

    @Test
    void testAddUniversity() {
        Universite u = new Universite();
        u.setNomUniversite("Université Test");
        u.setAdresse("Adresse Test");

        Universite savedUniversite = universiteService.addUniversity(u);

        assertNotNull(savedUniversite);
        assertEquals("Université Test", savedUniversite.getNomUniversite());
    }

    @Test
    void testRetrieveAllUniversities() {
        List<Universite> universites = universiteService.retrieveAllUniversities();
        assertNotNull(universites);
        assertTrue(universites.size() >= 0);
    }

    @Test
    void testRetrieveUniversity() {
        Universite u = new Universite();
        u.setNomUniversite("Université Test");
        u.setAdresse("Adresse Test");

        Universite savedUniversite = universiteService.addUniversity(u);

        Universite retrievedUniversite = universiteService.retrieveUniversity(savedUniversite.getIdUniversite());
        assertNotNull(retrievedUniversite);
        assertEquals(savedUniversite.getIdUniversite(), retrievedUniversite.getIdUniversite());
    }

    @Test
    void testUpdateUniversity() {
        Universite u = new Universite();
        u.setNomUniversite("Université Original");
        u.setAdresse("Adresse Originale");

        Universite savedUniversite = universiteService.addUniversity(u);

        savedUniversite.setNomUniversite("Université Modifiée");
        Universite updatedUniversite = universiteService.updateUniversity(savedUniversite);

        assertEquals("Université Modifiée", updatedUniversite.getNomUniversite());
    }

    @Test
    void testDesaffecterFoyerAUniversite() {
        Universite u = new Universite();
        u.setNomUniversite("Université avec Foyer");
        u.setAdresse("Adresse avec Foyer");

        Universite savedUniversite = universiteService.addUniversity(u);

        Universite desaffecterResult = universiteService.desaffecterFoyerAUniversite(savedUniversite.getIdUniversite());

        assertNotNull(desaffecterResult);
        assertNull(desaffecterResult.getFoyer());
    }

}


