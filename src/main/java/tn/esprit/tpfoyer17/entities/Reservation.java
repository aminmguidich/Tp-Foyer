package tn.esprit.tpfoyer17.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    String idReservation;

    LocalDate anneeUniversitaire;

    boolean estValide;

    @ToString.Exclude
    @ManyToMany
    @JsonIgnore
    Set<Etudiant> etudiants;
}
