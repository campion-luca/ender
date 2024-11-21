package lucacampion.ender.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private String nome;

    @OneToMany(mappedBy = "autore")
    private List<Evento> listaEventi;


    // costruttore generale
    public Autore(String nome) {
        this.listaEventi = new ArrayList<>();;
        this.nome = this.nome;
    }
}
