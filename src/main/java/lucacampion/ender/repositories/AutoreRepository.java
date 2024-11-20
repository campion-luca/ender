package lucacampion.ender.repositories;

import lucacampion.ender.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoreRepository extends JpaRepository<Autore, Long> {

    List<Autore> findByNome(String nomeAutore);
}
