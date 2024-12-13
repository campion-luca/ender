package lucacampion.ender.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="eventi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(name = "nome_evento")
    private String nomeEvento;
    @Column(name = "data_evento")
    private LocalDate dataEvento;
    private String descrizione;
    private Double prezzo;
    private String fotoEvento;
    private String luogo;
    @ManyToOne
    @JoinColumn(name="autore_id")
    @JsonManagedReference
    private Autore autore;
    @ManyToMany
    @JoinTable(
            name = "eventi_utente",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "utenti_id")
    )
    private List<Utente> utenti;



    // costruttore generale
    public Evento(Autore autore, LocalDate dataEvento, String descrizione, String luogo, String nomeEvento, Double prezzo, String fotoEvento) {
        this.autore = autore;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.nomeEvento = nomeEvento;
        this.prezzo = prezzo;
        this.fotoEvento = fotoEvento;
    }
}
