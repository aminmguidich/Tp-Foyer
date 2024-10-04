package tn.esprit.tpfoyer17.services.interfaces;

import tn.esprit.tpfoyer17.entities.Reservation;
import tn.esprit.tpfoyer17.entities.Universite;

import java.time.LocalDate;
import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversities();
    Universite addUniversity (Universite u);
    Universite updateUniversity (Universite u);
    Universite retrieveUniversity (long idUniversity);



    public Universite desaffecterFoyerAUniversite (  long idUniversite) ;

    public Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;

}
