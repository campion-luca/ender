package lucacampion.ender.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lucacampion.ender.entities.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {
    @Value("${jwt.secret}")  // collegamento del segreto!
    private String secret;

    public String createToken(Utente utente){
        // builder = creazione token
       return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis())) // data di emissione token (millisecondi)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // data di scadenza token (millisecondi)
               // 1000 = 1 secondo --- * 60 = 1 minuto --- * 60 = 1 ora --- * 24 = 1 giorno --- * 7 = 1 settimana
                .subject(String.valueOf(utente.getId())) // proprietario del token (NO DATI SENSIBILI)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // firma del token, necessiterò di hmac e un segreto
                .compact(); // assemblerà tutto in una stringa, ossia il mio token
    }

//    public void verifyToken(String accesToken){
//        // parse = verifica token
//        Jwts.parser()
//    }
}
