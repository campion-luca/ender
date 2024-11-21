package lucacampion.ender.services;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.exceptions.UnauthorizedException;
import lucacampion.ender.payloads.UserLoginDTO;
import lucacampion.ender.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWT jwt;


    public String checkCredentialsAndGenerateToken(UserLoginDTO body){
        // cerco nel DB se esiste l'utente con l'email fornita
        Utente found = this.utenteService.findByEmail(body.email());
        // verifico la password fornita che corrisponda al DB
        if(found.getPassword().equals(body.password())){
            // genero il token
            String accessToken = jwt.createToken(found);
            // ritorno il token
            return accessToken;
        } else {
            // credenziali errate
            throw new UnauthorizedException("Credenziali errate");
        }
    }

}
