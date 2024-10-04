package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Foyer;

@Repository
public interface FoyerRepository extends CrudRepository<Foyer,Long> {
    Foyer findByUniversiteIdUniversite(Long idUniversite);
}
