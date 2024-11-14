package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.entities.Universite;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UniversiteRepository extends CrudRepository<Universite,Long> {
    Universite findByNomUniversiteLike(String nomu);
    List<Reservation>
    findByFoyerBlocsChambresReservationsAnneeUniversitaireAndNomUniversite(
            LocalDate anneeUniversite,
                                                                                             String nomUniversite);

}
