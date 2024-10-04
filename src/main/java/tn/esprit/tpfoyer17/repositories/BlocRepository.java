package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Bloc;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {





















    List<Bloc> findByFoyerIdFoyer(long idFoyer);
    Bloc findByChambresIdChambre(Long idChambre);
    List<Bloc> findByFoyerNomFoyerLikeAndFoyerUniversiteAdresseLike(String nomFoyer, String adresse);
}
