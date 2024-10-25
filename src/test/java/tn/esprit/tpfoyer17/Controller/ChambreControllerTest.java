package tn.esprit.tpfoyer17.Controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer17.controllers.ChambreController;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.services.interfaces.IChambreService;

import java.util.ArrayList;
import java.util.List;

class ChambreControllerTest {

    @InjectMocks
    private ChambreController chambreController;

    @Mock
    private IChambreService chambreService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chambreController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testRetrieveAllChambres() throws Exception {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre(1L, 101, TypeChambre.SIMPLE, null, null));
        when(chambreService.retrieveAllChambres()).thenReturn(chambres);

        mockMvc.perform(get("/api/chambres/retrieveAllChambres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].numeroChambre").value(101));
    }

    @Test
    void testAddChambre() throws Exception {
        Chambre chambre = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null);
        when(chambreService.addChambre(any(Chambre.class))).thenReturn(chambre);

        mockMvc.perform(post("/api/chambres/addChambre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chambre)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroChambre").value(101));
    }

    @Test
    void testUpdateChambre() throws Exception {
        Chambre chambre = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null);
        when(chambreService.updateChambre(any(Chambre.class))).thenReturn(chambre);

        mockMvc.perform(put("/api/chambres/updateChambre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chambre)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroChambre").value(101));
    }

    @Test
    void testRetrieveChambre() throws Exception {
        Chambre chambre = new Chambre(1L, 101, TypeChambre.SIMPLE, null, null);
        when(chambreService.retrieveChambre(1L)).thenReturn(chambre);

        mockMvc.perform(get("/api/chambres/retrieveChambre/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numeroChambre").value(101));
    }

    @Test
    void testFindByTypeChambre() throws Exception {
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(new Chambre(1L, 101, TypeChambre.SIMPLE, null, null));
        when(chambreService.findByTypeChambre()).thenReturn(chambres);

        mockMvc.perform(get("/api/chambres/findByTypeChambre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].numeroChambre").value(101));
    }

    @Test
    void testAffecterChambresABloc() throws Exception {
        List<Long> numChambre = new ArrayList<>();
        numChambre.add(101L);
        Bloc bloc = new Bloc(); // Assuming a Bloc object is needed
        when(chambreService.affecterChambresABloc(anyList(), anyLong())).thenReturn(bloc);

        mockMvc.perform(put("/api/chambres/affecterChambresABloc/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(numChambre)))
                .andExpect(status().isOk());
    }




    // Add more tests for the remaining endpoints as needed
}
