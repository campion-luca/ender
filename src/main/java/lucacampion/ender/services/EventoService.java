package lucacampion.ender.services;

import lucacampion.ender.entities.Autore;
import lucacampion.ender.entities.Evento;
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

}
