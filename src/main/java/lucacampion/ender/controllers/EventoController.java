package lucacampion.ender.controllers;

import lucacampion.ender.entities.Evento;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.payloads.NuovoAutoreDTO;
import lucacampion.ender.payloads.NuovoEventoDTO;
import lucacampion.ender.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi") // prefisso comune per tutti gli endpoint
public class EventoController {
    @Autowired
    private EventoService eventoService;

    // GET all
    // http://localhost:3001/eventi
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Evento> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.eventoService.findAll(page, size, sortBy);
    }

    // GET tramite ID
    // http://localhost:3001/eventi/{eventoId}
    @GetMapping("/{eventoId}")
    public Evento findById(@PathVariable Long eventoId) {
        return this.eventoService.trovaEvento(eventoId);
    }

    // POST
    // http://localhost:3001/eventi + (payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Evento saveEvento(@RequestBody NuovoEventoDTO body) { return this.eventoService.save(body);}

    // UPDATE
    // http://localhost:3001/eventi/{eventoId} + (payload)
    @PutMapping("/{eventoId}")
    public Evento findByIdAndUpdate(@PathVariable Long eventoId, @RequestBody @Validated NuovoEventoDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.eventoService.findByIdAndUpdate(eventoId, body);
    }

    // DELETE
    // http://localhost:3001/eventi/{eventoId}
    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long eventoId) {
        this.eventoService.findByIdAndDelete(eventoId);
    }
}
