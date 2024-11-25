package lucacampion.ender.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lucacampion.ender.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter { // tasto destro + import methods

    @Autowired
    private JWT jwt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Verifichiamo se nella richiesta è presente l'Authorization Header e se ben formato --> 401
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer")) throw new UnavailableException("Inserire token nel formato corretto!");
        // 2. Estraiamo il token dall'header
        String accessToken = authHeader.substring(7); // tolgo i primi 7 caratteri --> Bearer + spazio
        // 3. Verifichiamo se il token è stato manipolato (verifico la firma) o se è scaduto (Expiratrion Date)
        jwt.verifyToken(accessToken);
        // 4. Se tutto è OK, andiamo avanti (passiamo la richiesta al prossimo filtro o al controller)
        filterChain.doFilter(request, response); // tramite .doFilter(req,res) richiamo il prossimo membro della catena
        // 5. Se qualcosa non va con il token --> 401
    }


    // disabilito il filtro per tutte le richieste al controller Auth, quindi tutte le richieste URL /auth/** non dovranno avere token controllo
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
