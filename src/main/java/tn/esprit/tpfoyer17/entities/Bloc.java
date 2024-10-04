package tn.esprit.tpfoyer17.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

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
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long idBloc;

    String nomBloc;

    long capaciteBloc;

    @ToString.Exclude
    @ManyToOne
    @JsonIgnore
    Foyer foyer;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "bloc")
    Set<Chambre> chambres;


}
