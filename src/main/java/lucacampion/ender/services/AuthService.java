package lucacampion.ender.services;

import lucacampion.ender.entities.Utente;
import lucacampion.ender.payloads.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body){
        // cerco nel DB se esiste l'utente con l'email fornita
        Utente found = this.utenteService.findByEmail(body.email());
        // verifico la password fornita che corrisponda al DB
        if(found.getPassword().equals(body.password())){

        } else {
            // credenziali errate
        }
    }

}
