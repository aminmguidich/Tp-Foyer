package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.impementations.UniversiteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceTestMockito {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite universite;
    private Foyer foyer;

    @BeforeEach
    public void setUp() {
        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Esprit");

        foyer = new Foyer();
        foyer.setIdFoyer(1L);
    }

    @Test
    public void testRetrieveAllUniversities() {
        List<Universite> universites = new ArrayList<>();
        universites.add(universite);

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversities();
        assertEquals(1, result.size());
        assertEquals("Esprit", result.get(0).getNomUniversite());

        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    public void testAddUniversity() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversity(universite);
        assertNotNull(result);
        assertEquals("Esprit", result.getNomUniversite());

        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testUpdateUniversity() {
        universite.setAdresse("New Address");
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversity(universite);
        assertNotNull(result);
        assertEquals("New Address", result.getAdresse());

        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testRetrieveUniversity() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversity(1L);
        assertNotNull(result);
        assertEquals("Esprit", result.getNomUniversite());

        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    public void testDesaffecterFoyerAUniversite() {
        universite.setFoyer(foyer);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.desaffecterFoyerAUniversite(1L);
        assertNotNull(result);
        assertNull(result.getFoyer());

        verify(universiteRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testAffecterFoyerAUniversite() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        when(universiteRepository.findByNomUniversiteLike("Esprit")).thenReturn(universite);
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.affecterFoyerAUniversite(1L, "Esprit");
        assertNotNull(result);
        assertEquals(foyer, result.getFoyer());

        verify(foyerRepository, times(1)).findById(1L);
        verify(universiteRepository, times(1)).findByNomUniversiteLike("Esprit");
        verify(universiteRepository, times(1)).save(universite);
    }
}
