package tn.esprit.tpfoyer17.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.services.interfaces.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("api/blocs")


public class BlocController {
    @GetMapping("/findByFoyerIdFoyer/{idFoyer}")
    public List<Bloc> findByFoyerIdFoyer(@PathVariable("idFoyer") long idFoyer) {
        return blocService.findByFoyerIdFoyer(idFoyer);
    }

    @GetMapping("/findByChambresIdChambre/{idChambre}")
    public Bloc findByChambresIdChambre(@PathVariable("idChambre") Long idChambre) {
        return blocService.findByChambresIdChambre(idChambre);
    }

    IBlocService blocService;

    @GetMapping("/retrieveBlocs")
    public List<Bloc> retrieveBlocs() {
        return blocService.retrieveBlocs();
    }


    @GetMapping("/retrieveBloc/{idBloc}")
    public Bloc retrieveBloc(@PathVariable("idBloc") long idBloc) {
        return blocService.retrieveBloc(idBloc);
    }


    @PostMapping("/addBloc")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }


    @PutMapping("/updateBloc")
    public Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.updateBloc(bloc);
    }


    @DeleteMapping("/removeBloc/{idBloc}")
    public void removeBloc(@PathVariable("idBloc") long idBloc) {
        blocService.removeBloc(idBloc);
    }
}
