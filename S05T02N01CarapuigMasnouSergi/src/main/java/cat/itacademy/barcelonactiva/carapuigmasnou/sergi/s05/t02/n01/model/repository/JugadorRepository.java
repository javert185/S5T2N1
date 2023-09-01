package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Integer> {  
}
