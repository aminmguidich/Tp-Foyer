package tn.esprit.tpfoyer17.testServiceFoyer;

        import org.junit.jupiter.api.MethodOrderer;
        import org.junit.jupiter.api.Order;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.TestMethodOrder;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import tn.esprit.tpfoyer17.entities.Bloc;
        import tn.esprit.tpfoyer17.entities.Foyer;
        import tn.esprit.tpfoyer17.entities.Universite;
        import tn.esprit.tpfoyer17.repositories.BlocRepository;
        import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
        import tn.esprit.tpfoyer17.services.interfaces.IFoyerService;

        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

        import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FoyerTestJUnit {

    @Autowired
    private IFoyerService foyerService;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private BlocRepository blocRepository;

    @Test
    @Order(1)
    void addFoyer() {
        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Foyer A");
        foyer.setCapaciteFoyer(200);

        Foyer savedFoyer = foyerService.addFoyer(foyer);
        assertNotNull(savedFoyer);
        assertEquals("Foyer A", savedFoyer.getNomFoyer());
        System.out.println("Add Foyer: Ok");
    }

    @Test
    @Order(2)
    void retrieveAllFoyers() {
        List<Foyer> foyers = foyerService.retrieveAllFoyers();
        assertNotNull(foyers);
        assertFalse(foyers.isEmpty());
        System.out.println("Retrieve All Foyers: Ok");
    }

    @Test
    @Order(3)
    void retrieveFoyer() {
        Foyer foyer = foyerService.retrieveFoyer(1L); // Adjust ID as necessary
        assertNotNull(foyer);
        assertEquals("Foyer A", foyer.getNomFoyer()); // Adjust based on your data
        System.out.println("Retrieve Foyer: Ok");
    }

    @Test
    @Order(4)
    void removeFoyer() {
        foyerService.removeFoyer(1L); // Adjust ID as necessary
        assertNull(foyerService.retrieveFoyer(1L));
        System.out.println("Remove Foyer: Ok");
    }

    @Test
    @Order(5)
    void updateFoyer() {
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(1L); // Adjust based on your data
        foyer.setNomFoyer("Updated Foyer");

        Foyer updatedFoyer = foyerService.updateFoyer(foyer);
        assertNotNull(updatedFoyer);
        assertEquals("Updated Foyer", updatedFoyer.getNomFoyer());
        System.out.println("Modify Foyer: Ok");
    }

    @Test
    @Order(6)
    void ajouterFoyerEtAffecterAUniversite() {
        Universite universite = new Universite();
        universite.setNomUniversite("Test University");
        universite = universiteRepository.save(universite);

        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc 1");
        bloc = blocRepository.save(bloc);

        Set<Bloc> blocs = new HashSet<>();
        blocs.add(bloc);

        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Foyer B");
        foyer.setCapaciteFoyer(300);
        foyer.setBlocs(blocs);

        Foyer savedFoyer = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, universite.getIdUniversite());

        assertNotNull(savedFoyer);
        assertEquals("Foyer B", savedFoyer.getNomFoyer());
        assertEquals(universite.getIdUniversite(), savedFoyer.getUniversite().getIdUniversite());
        System.out.println("Add and Assign Foyer to University: Ok");
    }
}

