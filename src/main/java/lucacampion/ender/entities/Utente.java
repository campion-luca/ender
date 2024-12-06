package lucacampion.ender.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"password", "role", "accountNonLocked", "credentialsNonExpired", "authorities", "enabled"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String nickname;
    private String fotoProfilo;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "utenti", fetch = FetchType.EAGER)
    private List<Evento> eventi;


    // costruttore generale SBAGLIATO, ORDINE ERRATO
//    public Utente(String cognome, String email, String nickname, String nome, String password) {
//        this.cognome = cognome;
//        this.email = email;
//        this.eventi = new ArrayList<>(); // inizializzo la lista vuota, un utente appena creato non ha subito eventi pratecipanti ma potrà averli
//        this.nickname = nickname;
//        this.nome = nome;
//        this.password = password;
//    }
    public Utente(String nome, String cognome, String email, String nickname, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.eventi = new ArrayList<>(); // inizializzo la lista vuota, un utente appena creato non ha subito eventi pratecipanti ma potrà averli
        this.role = Role.USER; // iniziano tutti come "USER base", solo dopo la verifica di Carta d'identità etc.. potrò certificarlo
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}

// NOT WORKING
//(strategy = GenerationType.IDENTITY)