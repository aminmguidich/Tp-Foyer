package tn.esprit.tpfoyer17.services.impementations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.interfaces.IUniversiteService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniversiteService implements IUniversiteService {
    UniversiteRepository universiteRepository;
    FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        return (List<Universite>) universiteRepository.findAll();
    }

    @Override
    public Universite addUniversity(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversity(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversity(long idUniversity) {
        return universiteRepository.findById(idUniversity).orElse(null);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        var universite = universiteRepository.findById(idUniversite).orElse(null);

        if (universite != null) {
            universite.setFoyer(null);
            return universiteRepository.save(universite);
        }
        return null;
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        var foyer = foyerRepository.findById(idFoyer).orElse(null);
        var universite = universiteRepository.findByNomUniversiteLike(nomUniversite);

        if (universite != null) {
            universite.setFoyer(foyer);
            return universiteRepository.save(universite);
        }
        return null;
    }
}
