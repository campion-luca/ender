package lucacampion.ender.services;

import lucacampion.ender.entities.Autore;
import lucacampion.ender.exceptions.NotFoundException;
import lucacampion.ender.repositories.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoreService {
    @Autowired
    AutoreRepository autoreRepository;


    // FIND BY NOME
    public Autore trovaAutore(String nomeAutore) {
        if(autoreRepository.findByNome(nomeAutore).isEmpty()){
            throw new NotFoundException("L'autore " + nomeAutore + " non è stato trovato");
        }
        return autoreRepository.findByNome(nomeAutore).getFirst();
    }
}
