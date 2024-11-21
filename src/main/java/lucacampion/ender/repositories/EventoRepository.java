package lucacampion.ender.repositories;

import lucacampion.ender.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EventoRepository extends JpaRepository<Evento, Long> {
//    Optional<Evento> findById(Long eventoId);
}
