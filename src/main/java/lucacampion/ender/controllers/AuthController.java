package lucacampion.ender.controllers;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.BadRequestException;
import lucacampion.ender.payloads.NuovoUtenteDTO;
import lucacampion.ender.payloads.UserLoginDTO;
import lucacampion.ender.payloads.UserLoginResponseDTO;
import lucacampion.ender.services.AuthService;
import lucacampion.ender.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UtenteService utenteService;


    // http://localhost:3001/auth/login + (payload)
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body){
        return new UserLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    // POST
    // http://localhost:3001/auth/register + (payload)
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Utente saveUtente(@RequestBody NuovoUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel Payload!" + message);
        }
        return this.utenteService.save(body);
    }
}
