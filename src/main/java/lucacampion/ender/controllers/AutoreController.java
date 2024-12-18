package lucacampion.ender.controllers;

import lucacampion.ender.entities.Autore;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.payloads.NuovoAutoreDTO;
import lucacampion.ender.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autori") // prefisso comune per tutti gli endpoint
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    // Gli autori saranno pubblici per tutti, però per l'aggiunta o l'eliminazione solo l'ADMIN o l'ORGANIZZATORE stesso potrà farlo

    // GET all
    // http://localhost:3001/autori
    @GetMapping
    public Page<Autore> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.autoreService.findAll(page, size, sortBy);
    }

    // GET tramite nome
    // http://localhost:3001/autori/{nomeAutore}
    @GetMapping("/{nomeAutore}")
    public Autore findByNome(@PathVariable String nomeAutore) {
        return this.autoreService.trovaAutore(nomeAutore);
    }

    // POST
    // http://localhost:3001/autori + (payload)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Autore saveAutore(@RequestBody NuovoAutoreDTO body) { return this.autoreService.save(body);}


    // UPDATE
    // http://localhost:3001/autori/{nomeAutore} + (payload)
    @PutMapping("/{nomeAutore}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ORGANIZZATORE')")
    public Autore findByNomeAndUpdate(@PathVariable String nomeAutore, @RequestBody @Validated NuovoAutoreDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.autoreService.findByNomeAndUpdate(nomeAutore, body);
    }


    // DELETE
    // http://localhost:3001/autori/{nomeAutore}
    @DeleteMapping("/{nomeAutore}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByNomeAndDelete(@PathVariable String nomeAutore) {
        this.autoreService.findByNameAndDelete(nomeAutore);
    }
}
