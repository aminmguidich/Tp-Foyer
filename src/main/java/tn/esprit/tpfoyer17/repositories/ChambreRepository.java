package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends CrudRepository<Chambre, Long> {
    List<Chambre> findByNumeroChambreIn(List<Long> numeroChambreList);
    List<Chambre> findByBlocIdBloc(Long idBloc);
    List<Chambre> findByTypeChambreAndReservationsEstValide(TypeChambre typeChambre, boolean estValide);
    List<Chambre> findByBlocFoyerCapaciteFoyerGreaterThan(long value);
    Chambre findByReservationsIdReservation(String reservationsIdReservation);
    List<Chambre> findByBlocFoyerUniversiteNomUniversiteLike(String nomUni);
    List<Chambre> findByBlocIdBlocAndTypeChambre(long idBloc, TypeChambre typeChambre);

    @Query("SELECT chambre FROM Chambre chambre WHERE chambre.bloc.idBloc = :idBloc AND chambre.typeChambre = :typeChambre")
    List<Chambre> recupererParBlocEtTypeChambre(@Param("idBloc") long idBloc, @Param("typeChambre") TypeChambre typeChambre);

    @Query("SELECT chambre FROM Chambre chambre INNER JOIN " +
            "chambre.bloc.foyer.universite universite " +
            "WHERE universite.nomUniversite = :nomUniversite " +
            "AND (SELECT COUNT(r) FROM chambre.reservations r) = 0 " +
            "AND chambre.typeChambre = :typeChambre")
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@Param("nomUniversite") String nomUniversite, @Param("typeChambre") TypeChambre typeChambre);

    @Query("SELECT chambre FROM Chambre chambre WHERE (SELECT COUNT(r) FROM chambre.reservations r) = 0")
    List<Chambre> getChambresNonReserve();
}
