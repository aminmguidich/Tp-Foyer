package tn.esprit.tpfoyer17.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idFoyer;

    String nomFoyer;

    long capaciteFoyer;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne(mappedBy = "foyer")
    Universite universite;

    @ToString.Exclude
    //@JsonIgnore
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL)
    Set<Bloc> blocs;



}
