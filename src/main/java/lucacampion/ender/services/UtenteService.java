package lucacampion.ender.services;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.exceptions.NotFoundException;
import lucacampion.ender.payloads.NuovoUtenteDTO;
import lucacampion.ender.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    // SAVE NUOVO UTENTE
    public Utente save(NuovoUtenteDTO body) {
        Utente newUtente = new Utente(body.nome(),body.cognome(),body.email(),body.nickname(), bcrypt.encode(body.password()), body.fotoProfilo(), body.role());
        return this.utenteRepository.save(newUtente);
    }

    // FIND BY EMAIL
    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con mail " + email + " non è stato trovato!"));
    }

    // FIND BY ID
    public Utente trovaUtente(Long utenteId) {
        if(utenteRepository.findById(utenteId).isEmpty()) {
            throw new NotFoundException("L'utente con id " + utenteId + " non è stato trovato!");
        }
        return utenteRepository.findById(utenteId).get();
    }

    // FIND ALL
    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }

    // FIND BY ID AND UPDATE
    public Utente findByIdAndUpdate(Long utenteId, NuovoUtenteDTO body) {
        Utente found = this.trovaUtente(utenteId);
        if (!found.getEmail().equals(body.email())) { // uso l'email, così posso modificare il nickname
            throw new BadRequestException("L'utente con id " + utenteId + " non è stato trovato!");
        }
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());
        found.setNickname(body.nickname());
        found.setPassword(body.password());
        found.setFotoProfilo(body.fotoProfilo());
        return this.utenteRepository.save(found);
    }

    // FIND AND DELETE
    public void findByIdAndDelete(Long utenteId) {
        Utente found = this.trovaUtente(utenteId);
        this.utenteRepository.delete(found);
    }

}
