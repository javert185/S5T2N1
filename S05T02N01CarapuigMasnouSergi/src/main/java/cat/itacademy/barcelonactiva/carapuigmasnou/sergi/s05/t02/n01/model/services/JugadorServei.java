package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.PartidaRepository;

@Service
public class JugadorServei {
	
	private final JugadorRepository jugadorRepository;
	
	private final PartidaRepository partidaRepository;

	@Autowired
    public JugadorServei(JugadorRepository jugadorRepository, PartidaRepository partidaRepository) {
        this.jugadorRepository = jugadorRepository;
        this.partidaRepository = partidaRepository; // Inject partidaRepository
    }

   // @Override
    public Jugador crearJugador(Jugador jugador) {   
        jugadorRepository.save(jugador);
        return jugador;
    }
    
    public Partida crearPartida(Partida partida) {   
        partidaRepository.save(partida);
        return partida;
    }
    
    public List<Partida> findPartidasByJugadorId(Integer jugadorId) {
        return partidaRepository.findByJugadorId(jugadorId);
    }
  }


    