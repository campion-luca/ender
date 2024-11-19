package lucacampion.ender.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name="utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String nickname;
    private String fotoProfilo;

    @ManyToMany(mappedBy = "utenti")
    private List<Evento> eventi;
}
