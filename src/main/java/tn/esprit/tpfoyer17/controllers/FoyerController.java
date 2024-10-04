package tn.esprit.tpfoyer17.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.services.interfaces.IFoyerService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("api/foyers")
public class FoyerController {
    @GetMapping("/retrieveAllFoyers")
    public List<Foyer> retrieveAllFoyers() {
        return foyerService.retrieveAllFoyers();
    }
@PostMapping("/addFoyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }
@PutMapping("/updateFoyer")
    public Foyer updateFoyer( @RequestBody Foyer f) {
        return foyerService.updateFoyer(f);
    }
@GetMapping("/retrieveFoyer/{idFoyer}")
    public Foyer retrieveFoyer(@PathVariable("idFoyer") long idFoyer) {
        return foyerService.retrieveFoyer(idFoyer);
    }
@DeleteMapping("/removeFoyer")
    public void removeFoyer(@PathVariable("idFoyer") long idFoyer) {
        foyerService.removeFoyer(idFoyer);
    }
@PutMapping("/ajouterFoyerEtAffecterAUniversite/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @PathVariable("idUniversite") long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }

    IFoyerService foyerService;
}
