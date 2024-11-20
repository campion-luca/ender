package lucacampion.ender.controllers;

import lucacampion.ender.entities.Evento;
import lucacampion.ender.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;


    // VISUALIZZA TUTTI GLI EVENTI
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Evento> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.eventoService.findAll(page, size, sortBy);
    }
}
