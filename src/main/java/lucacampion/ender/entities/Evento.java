package lucacampion.ender.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="eventi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String nomeEvento;
    private Date dataEvento;
    private String descrizione;
    private String fotoEvento;
    private Double prezzo;
    private String luogo;

    @ManyToOne
    @JoinColumn(name="autore_id")
    private Autore autore;

    @ManyToMany
    @JoinTable(
            name = "eventi_utente",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "utenti_id")
    )
    private List<Utente> utenti;
}
