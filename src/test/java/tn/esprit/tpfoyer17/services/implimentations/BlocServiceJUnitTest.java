package tn.esprit.tpfoyer17.services.implimentations;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlocServiceJUnitTest {

    @Autowired
    private BlocService blocService;

    @Autowired
    private BlocRepository blocRepository;

    @Test
    @Order(1)
    void addBloc() {
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(100);

        Bloc savedBloc = blocService.addBloc(bloc);
        assertNotNull(savedBloc);
        assertEquals("Bloc A", savedBloc.getNomBloc());
        System.out.println("Add Bloc: Ok");
    }

    @Test
    @Order(2)
    void retrieveAllBlocs() {
        List<Bloc> blocs = blocService.retrieveBlocs();
        assertNotNull(blocs);
        assertFalse(blocs.isEmpty());
        System.out.println("Retrieve All Blocs: Ok");
    }

    @Test
    @Order(3)
    void retrieveBloc() {
        Bloc bloc = blocService.retrieveBloc(1L); // Adjust ID as necessary
        assertNotNull(bloc);
        assertEquals("Bloc A", bloc.getNomBloc()); // Adjust based on your data
        System.out.println("Retrieve Bloc: Ok");
    }

    @Test
    @Order(5)
    void modifyBloc() {
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L); // Adjust based on your data
        bloc.setNomBloc("Updated Bloc");

        Bloc updatedBloc = blocService.updateBloc(bloc);
        assertNotNull(updatedBloc);
        assertEquals("Updated Bloc", updatedBloc.getNomBloc());
        System.out.println("Modify Bloc: Ok");
    }
}