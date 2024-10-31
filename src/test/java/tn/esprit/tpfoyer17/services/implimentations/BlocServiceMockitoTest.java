package tn.esprit.tpfoyer17.services.implimentations;


import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlocServiceMockitoTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocService blocService;

    private Bloc bloc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bloc = Bloc.builder()
                .idBloc(1L)
                .nomBloc("Bloc A")
                .capaciteBloc(50)
                .build();
    }

    @Test
    void addBloc() {
        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc savedBloc = blocService.addBloc(bloc);

        assertNotNull(savedBloc);
        assertEquals("Bloc A", savedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void retrieveAllBlocs() {
        when(blocRepository.findAll()).thenReturn(Arrays.asList(bloc));

        List<Bloc> result = blocService.retrieveBlocs();

        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void retrieveBloc() {
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        Bloc foundBloc = blocService.retrieveBloc(1L);

        assertNotNull(foundBloc);
        verify(blocRepository, times(1)).findById(1L);
    }

    @Test
    void removeBloc() {
        doNothing().when(blocRepository).deleteById(1L);

        blocService.removeBloc(1L);

        verify(blocRepository, times(1)).deleteById(1L);
    }

    @Test
    void removeBloc_NotFound() {
        doThrow(new EntityNotFoundException("Bloc not found")).when(blocRepository).deleteById(1L);

        assertThrows(EntityNotFoundException.class, () -> blocService.removeBloc(1L));
        verify(blocRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateBloc() {
        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc updatedBloc = blocService.updateBloc(bloc);

        assertNotNull(updatedBloc);
        assertEquals("Bloc A", updatedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void findByFoyerIdFoyer() {
        when(blocRepository.findByFoyerIdFoyer(10L)).thenReturn(Arrays.asList(bloc));

        List<Bloc> result = blocService.findByFoyerIdFoyer(10L);

        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findByFoyerIdFoyer(10L);
    }

    @Test
    void findByChambresIdChambre() {
        when(blocRepository.findByChambresIdChambre(5L)).thenReturn(bloc);

        Bloc result = blocService.findByChambresIdChambre(5L);

        assertNotNull(result);
        verify(blocRepository, times(1)).findByChambresIdChambre(5L);
    }
}