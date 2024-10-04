package tn.esprit.tpfoyer17.controllers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.services.interfaces.IUniversiteService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("api/univeristes")
public class UniversiteController {
    @GetMapping("/retrieveAllUniversities")
    public List<Universite> retrieveAllUniversities() {
        return universiteService.retrieveAllUniversities();
    }
@PostMapping("/addUniversity")
    public Universite addUniversity(@RequestBody Universite u) {
        return universiteService.addUniversity(u);
    }
@PutMapping("/updateUniversity")
    public Universite updateUniversity(@RequestBody Universite u) {
        return universiteService.updateUniversity(u);
    }
@GetMapping("/retrieveUniversity/{idUniversity}")
    public Universite retrieveUniversity(@PathVariable("idUniversity") long idUniversity) {
        return universiteService.retrieveUniversity(idUniversity);
    }
@PutMapping("/affecterFoyerAUniversite/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable("idFoyer") long idFoyer, @PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }
@PutMapping("/desaffecterFoyerAUniversite/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(
                                                  @PathVariable("idUniversite") long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite( idUniversite);
    }

    IUniversiteService universiteService;
}
