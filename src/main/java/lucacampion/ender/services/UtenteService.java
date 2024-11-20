package lucacampion.ender.services;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.NotFoundException;
import lucacampion.ender.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;


    public Utente findByEmail(String email){
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con mail " + email + " non è stato trovato!"));
    }
}
