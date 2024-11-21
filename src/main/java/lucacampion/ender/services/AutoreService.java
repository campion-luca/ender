package lucacampion.ender.services;

import lucacampion.ender.entities.Autore;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.exceptions.NotFoundException;
import lucacampion.ender.payloads.NuovoAutoreDTO;
import lucacampion.ender.repositories.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {
    @Autowired
    AutoreRepository autoreRepository;


    // SALVA NUOVO AUTORE
    public Autore save(NuovoAutoreDTO body) {
        List<Autore> autori = this.autoreRepository.findByNome(body.nome());
        if (!autori.isEmpty()) {
            throw new BadRequestException("L'autore " + body.nome() + " è già esistente!");
        };

        Autore newAutore = new Autore(body.nome());
        return this.autoreRepository.save(newAutore);
    }

    // FIND ALL
    public Page<Autore> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.autoreRepository.findAll(pageable);
    }

    // FIND BY NOME
    public Autore trovaAutore(String nomeAutore) {
        if(autoreRepository.findByNome(nomeAutore).isEmpty()){
            throw new NotFoundException("L'autore " + nomeAutore + " non è stato trovato");
        }
        return autoreRepository.findByNome(nomeAutore).getFirst();
    }

    // FIND BY NOME AND UPDATE
    public Autore findByNomeAndUpdate(String nomeAutore, NuovoAutoreDTO body) {
        Autore found = this.trovaAutore(nomeAutore);
        if (!found.getNome().equals(body.nome())) {
            throw new BadRequestException("L'autore " + body.nome() + " è già in uso");
        }
        found.setNome(body.nome());
        return this.autoreRepository.save(found);
    }

    // FIND AND DELETE
    public void findByNameAndDelete(String nomeAutore) {
        Autore found = this.trovaAutore(nomeAutore);
        this.autoreRepository.delete(found);
    }
}
