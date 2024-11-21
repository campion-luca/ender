package lucacampion.ender.services;

import lucacampion.ender.entities.Evento;
import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.NotFoundException;
import lucacampion.ender.payloads.NuovoUtenteDTO;
import lucacampion.ender.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;


    // SAVE NUOVO UTENTE
    public Utente save(NuovoUtenteDTO body) {
        Utente newUtente = new Utente(body.nome(),body.cognome(),body.email(),body.nickname(),body.pasword());
        return this.utenteRepository.save(newUtente);
    }

    // FIND BY EMAIL
    public Utente findByEmail(String email){
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con mail " + email + " non è stato trovato!"));
    }

    // FIND ALL
    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }

}
