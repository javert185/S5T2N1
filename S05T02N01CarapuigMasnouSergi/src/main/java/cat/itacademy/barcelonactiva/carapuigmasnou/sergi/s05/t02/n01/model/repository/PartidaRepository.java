package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Partida;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PartidaRepository extends JpaRepository<Partida, Integer> {
	
	List<Partida> findByJugadorId(Integer id);
}
