package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.UniversiteService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversityServiceUnitTest {

    @InjectMocks
    private UniversiteService universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversities() {
        Universite universite1 = Universite.builder().idUniversite(1L).nomUniversite("Univ1").adresse("Address1").build();
        Universite universite2 = Universite.builder().idUniversite(2L).nomUniversite("Univ2").adresse("Address2").build();

        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));

        List<Universite> universities = universiteService.retrieveAllUniversities();

        assertNotNull(universities);
        assertEquals(2, universities.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversity() {
        Universite universite = Universite.builder().idUniversite(1L).nomUniversite("Univ1").adresse("Address1").build();

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite savedUniversity = universiteService.addUniversity(universite);

        assertNotNull(savedUniversity);
        assertEquals("Univ1", savedUniversity.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testUpdateUniversity() {
        Universite universite = Universite.builder().idUniversite(1L).nomUniversite("UnivUpdated").adresse("AddressUpdated").build();

        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite updatedUniversity = universiteService.updateUniversity(universite);

        assertNotNull(updatedUniversity);
        assertEquals("UnivUpdated", updatedUniversity.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversity() {
        Universite universite = Universite.builder().idUniversite(1L).nomUniversite("Univ1").adresse("Address1").build();

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite foundUniversity = universiteService.retrieveUniversity(1L);

        assertNotNull(foundUniversity);
        assertEquals(1L, foundUniversity.getIdUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testDesaffecterFoyerAUniversite() {
        Universite universite = Universite.builder().idUniversite(1L).nomUniversite("Univ1").foyer(new Foyer()).build();

        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite updatedUniversity = universiteService.desaffecterFoyerAUniversite(1L);

        assertNotNull(updatedUniversity);
        assertNull(updatedUniversity.getFoyer());
        verify(universiteRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testAffecterFoyerAUniversite() {
        Foyer foyer = new Foyer();
        Universite universite = Universite.builder().idUniversite(1L).nomUniversite("Univ1").adresse("Address1").build();

        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversiteLike("Univ1")).thenReturn(universite);
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite updatedUniversity = universiteService.affecterFoyerAUniversite(1L, "Univ1");

        assertNotNull(updatedUniversity);
        assertEquals(foyer, updatedUniversity.getFoyer());
        verify(foyerRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).findByNomUniversiteLike("Univ1");
        verify(universiteRepository, times(1)).save(universite);
    }
}