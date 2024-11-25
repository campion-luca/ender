package lucacampion.ender.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {
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

    @ManyToMany(mappedBy = "utenti")
    private List<Evento> eventi;


    // costruttore generale
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
    }


}

// NOT WORKING
//(strategy = GenerationType.IDENTITY)