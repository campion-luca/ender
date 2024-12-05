package lucacampion.ender.controllers;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.payloads.NuovoUtenteDTO;
import lucacampion.ender.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/utenti") // prefisso comune per tutti gli endpoint
public class UtenteController {
    @Autowired
    private UtenteService utenteService;


    // Solo l'Admin può accedere ai vari account degli utenti, vederli e cercarli o addirittura eliminarli.
    // Sarà sempre l'Admin poi a fare l'upgrade degli stessi.

    // GET all
    // http://localhost:3001/utenti
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }

    // POST
    // http://localhost:3001/eventi + (payload)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Utente saveUtente(@RequestBody NuovoUtenteDTO body) { return this.utenteService.save(body);}


    // **************************************************** ME ENDPOINT ****************************************************
    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticaticatedUser){
        return currentAuthenticaticatedUser;
    }

    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente currentAuthenticaticatedUser, @RequestBody @Validated NuovoUtenteDTO body) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticaticatedUser.getId(), body);
    }

    @DeleteMapping("/me")
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticaticatedUser) {
        this.utenteService.findByIdAndDelete(currentAuthenticaticatedUser.getId());
    }
    // ********************************************************************************************************


    // GET tramite ID
    // http://localhost:3001/utenti/{utenteId}
    @GetMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findById(@PathVariable Long utenteId) {
        return this.utenteService.trovaUtente(utenteId);
    }

    // UPDATE
    // http://localhost:3001/utenti/{utenteId} + (payload)
    @PutMapping("/{utenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long utenteId) {
        this.utenteService.findByIdAndDelete(utenteId);
    }
}
