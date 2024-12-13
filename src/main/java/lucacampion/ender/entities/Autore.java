package lucacampion.ender.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "nome_autore", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "autore", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Evento> listaEventi;


    // costruttore generale
    public Autore(String nome) {
        this.listaEventi = new ArrayList<>();;
        this.nome = nome;
    }
}
