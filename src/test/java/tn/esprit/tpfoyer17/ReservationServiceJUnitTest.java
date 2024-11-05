package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.services.impementations.ReservationService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationServiceJUnitTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    @Order(1)
    void addReservation() {
        Reservation reservation = Reservation.builder()
                .idReservation("2023-BLOC-A")
                .anneeUniversitaire(LocalDate.now())
                .estValide(true)
                .build();

        Reservation savedReservation = reservationService.updateReservation(reservation);
        assertNotNull(savedReservation);
        assertEquals("2023-BLOC-A", savedReservation.getIdReservation());
        System.out.println("Add Reservation: Ok");
    }

    @Test
    @Order(2)
    void retrieveAllReservations() {
        List<Reservation> reservations = reservationService.retrieveAllReservation();
        assertNotNull(reservations);
        assertFalse(reservations.isEmpty());
        System.out.println("Retrieve All Reservations: Ok");
    }

    @Test
    @Order(3)
    void retrieveReservation() {
        Reservation reservation = reservationService.retrieveReservation("2023-BLOC-A");
        assertNotNull(reservation);
        assertEquals("2023-BLOC-A", reservation.getIdReservation());
        System.out.println("Retrieve Reservation: Ok");
    }


}
