package tn.esprit.tpfoyer17.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer17.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Reservation findByIdReservation(String id);


    @Query("SELECT DISTINCT reservation FROM Reservation reservation ," +
            " Universite universite" +
            " INNER JOIN universite.foyer.blocs bloc" +
            " INNER JOIN bloc.chambres chambre" +
            " INNER JOIN chambre.reservations" +
            " WHERE universite.nomUniversite = :nomUniversite" +
            " AND YEAR(reservation.anneeUniversitaire) = YEAR( :anneeUniversitaire)")
    List<Reservation> recupererParBlocEtTypeChambre(
            @Param("nomUniversite") String nomUniversite,
            @Param("anneeUniversitaire") LocalDate anneeUniversitaire);


}
