package tn.esprit.tpfoyer17.services.impementations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.entities.Universite;
import tn.esprit.tpfoyer17.entities.enumerations.TypeChambre;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;
import tn.esprit.tpfoyer17.repositories.UniversiteRepository;
import tn.esprit.tpfoyer17.services.interfaces.IChambreService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChambreService implements IChambreService {
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    UniversiteRepository universiteRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        return (List<Chambre>) chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public List<Chambre> findByTypeChambre() {
        return chambreRepository.findByTypeChambreAndReservationsEstValide(TypeChambre.DOUBLE, true);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
       Bloc bloc = blocRepository.findById(idBloc).orElse(null);
       //List<Chambre> chambreList = (List<Chambre>) chambreRepository.findAllById(numChambre);
       List<Chambre> chambreList =  chambreRepository.findByNumeroChambreIn(numChambre);

       for(Chambre chambre: chambreList) {
           chambre.setBloc(bloc);
           chambreRepository.save(chambre);
       }
        return bloc;
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findByBlocFoyerUniversiteNomUniversiteLike(nomUniversite);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocIdBlocAndTypeChambre(idBloc,typeC);
    }

    @Override
    public List<Chambre> getChambresParBlocEtTypeJPQL(long idBloc, TypeChambre typeC) {
        return chambreRepository.recupererParBlocEtTypeChambre(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return chambreRepository.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite,type);
    }

    @Scheduled(cron = "*/10 * * * * *" )

    @Override
    public void getChambresNonReserve() {

        for (Chambre chambre : chambreRepository.getChambresNonReserve())
        {
            log.info(chambre.toString());
        }
    }
}
