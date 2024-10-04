package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends CrudRepository<Chambre, Long> {

















    List<Chambre> findByNumeroChambreIn(List<Long> numeroChambreList);

    List<Chambre> findByBlocIdBloc(Long idBloc);

    List<Chambre> findByTypeChambreAndReservationsEstValide(TypeChambre typeChambre, boolean estvalide);

    List<Chambre> findByBlocFoyerCapaciteFoyerGreaterThan(long value);

    Chambre findByReservationsIdReservation(String reservations_idReservation);

    List<Chambre> findByBlocFoyerUniversiteNomUniversiteLike(String nomUni);

    public List<Chambre> findByBlocIdBlocAndTypeChambre(long idBloc, TypeChambre typeC);


    @Query("SELECT chambre FROM Chambre chambre WHERE chambre.bloc.idBloc = :idbloc AND chambre.typeChambre = :typechambre")
    List<Chambre> recupererParBlocEtTypeChambre(@Param("idbloc") long idbloc , @Param("typechambre") TypeChambre typechambre );

    @Query("SELECT chambre FROM Chambre chambre INNER JOIN " +
            "chambre.bloc.foyer.universite universite " +
            " WHERE universite.nomUniversite = :nomUniversite " +
            "AND (SELECT COUNT(r) FROM chambre.reservations r) = 0" +
            "AND chambre.typeChambre = :typechambre"
    )
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@Param("nomUniversite") String nomUniversite , @Param("typechambre") TypeChambre typechambre );

    @Query("SELECT chambre FROM Chambre chambre " +
        //    "INNER JOIN chambre.bloc.foyer.universite universite " +
            " WHERE (SELECT COUNT(r) FROM chambre.reservations r) = 0"
    )
    List<Chambre> getChambresNonReserve();

}
