package lucacampion.ender.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="autori")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "nome_autore")
    private String nomeAutore;

    @OneToMany(mappedBy = "autore")
    private List<Evento> listaEventi;


    // costruttore generale
    public Autore(List<Evento> listaEventi, String nomeAutore) {
        this.listaEventi = listaEventi;
        this.nomeAutore = nomeAutore;
    }
}
