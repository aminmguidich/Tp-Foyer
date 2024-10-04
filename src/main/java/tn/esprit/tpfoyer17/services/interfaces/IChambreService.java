package tn.esprit.tpfoyer17.services.interfaces;


import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre addChambre(Chambre c); // ajouter l’équipe avec son détail
    Chambre updateChambre (Chambre c);
    Chambre retrieveChambre (long idChambre);

    List<Chambre> findByTypeChambre();
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) ;
    public List<Chambre>  getChambresParNomUniversite( String nomUniversite) ;
    public List<Chambre> getChambresParBlocEtType (long idBloc, TypeChambre typeC) ;
    public List<Chambre> getChambresParBlocEtTypeJPQL (long idBloc, TypeChambre typeC) ;
    public List<Chambre>  getChambresNonReserveParNomUniversiteEtTypeChambre( String nomUniversite,TypeChambre type) ;
    public void getChambresNonReserve() ;
}
