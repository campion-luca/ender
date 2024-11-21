package lucacampion.ender.services;

import lucacampion.ender.entities.Autore;
import lucacampion.ender.entities.Evento;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.exceptions.NotFoundException;
import lucacampion.ender.payloads.NuovoEventoDTO;
import lucacampion.ender.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private AutoreService autoreService;


    // SALVA NUOVO EVENTO
    public Evento save(NuovoEventoDTO body) {
        Autore foundAutore = autoreService.trovaAutore(body.autore());
        Evento newEvento = new Evento(foundAutore,body.dataEvento(),body.descrizione(),body.luogo(),body.nome(),body.prezzo());
        return this.eventoRepository.save(newEvento);
    }

    // FIND ALL
    public Page<Evento> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventoRepository.findAll(pageable);
    }

    // FIND BY ID
    public Evento trovaEvento(Long eventoId) {
        if(eventoRepository.findById(eventoId).isEmpty()) {
            throw new NotFoundException("L'evento con id " + eventoId + " non è stato trovato!");
        }
        return eventoRepository.findById(eventoId).get();
    }

    // FIND BY ID AND UPDATE
    public Evento findByIdAndUpdate(Long eventoId, NuovoEventoDTO body) {
        Evento found = this.trovaEvento(eventoId);
        if (!found.getNomeEvento().equals(body.nome())) {
            throw new BadRequestException("L'evento con id " + eventoId + " non è stato trovato!");
        }
        // cerco l'autore dato in input tramite Long ID
        Autore autore = autoreService.findAutoreById(eventoId);
        // lo uso per cambiare il vecchio autore
        found.setAutore(autore);
        found.setDataEvento(body.dataEvento());
        found.setLuogo(body.luogo());
        found.setNomeEvento(body.nome());
        found.setDescrizione(body.descrizione());
        found.setPrezzo(body.prezzo());
        return this.eventoRepository.save(found);
    }

    // FIND AND DELETE
    public void findByIdAndDelete(Long eventoId) {
        Evento found = this.trovaEvento(eventoId);
        this.eventoRepository.delete(found);
    }
}
