package lucacampion.ender.controllers;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.payloads.NuovoUtenteDTO;
import lucacampion.ender.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti") // prefisso comune per tutti gli endpoint
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    // GET all
    // http://localhost:3001/utenti
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }

    // GET tramite ID
    // http://localhost:3001/utenti/{utenteId}
    @GetMapping("/{utenteId}")
    public Utente findById(@PathVariable Long utenteId) {
        return this.utenteService.trovaUtente(utenteId);
    }

    // POST
    // http://localhost:3001/utenti + (payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Utente saveUtente(@RequestBody NuovoUtenteDTO body) { return this.utenteService.save(body);}

    // UPDATE
    // http://localhost:3001/utenti/{utenteId} + (payload)
    @PutMapping("/{utenteId}")
    public Utente findByIdAndUpdate(@PathVariable Long utenteId, @RequestBody @Validated NuovoUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.utenteService.findByIdAndUpdate(utenteId, body);
    }

    // DELETE
    // http://localhost:3001/utenti/{utenteId}
    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long utenteId) {
        this.utenteService.findByIdAndDelete(utenteId);
    }
}
