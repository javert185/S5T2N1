package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.PartidaRepository;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;
    
    @Autowired
    private JugadorRepository jugadorRepository;

    public String calculateWinPercentageForPlayer(int playerId) {
        List<Partida> playerGames = partidaRepository.findByJugadorId(playerId);
        int totalPartides = playerGames.size();
        int guanyades = 0;

        for (Partida partida : playerGames) {
            if (partida.isGuanyada()) {
                guanyades++;
            }
        }

        if (totalPartides == 0) {
            return ""; // Handle the case when there are no games to prevent division by zero.
        }

        double percentatgeGuanyades = (guanyades * 100.0) / totalPartides;
        String percentatgeGuanyadesFormatted = String.format("%.2f", percentatgeGuanyades) + "%";
        return percentatgeGuanyadesFormatted;
    }
    
    public List<String> calculateWinPercentagesForPlayers(List<Integer> playerIds) {
        List<String> winPercentages = new ArrayList<>();

        for (Integer playerId : playerIds) {
            String percentage = calculateWinPercentageForPlayer(playerId);
            winPercentages.add(percentage);
        }

        return winPercentages;
    }


    public String getBestScore() {
        List<Jugador> allPlayers = jugadorRepository.findAll();
        String bestPlayerScore = "";

        double bestScore = Double.MIN_VALUE;

        for (Jugador jugador : allPlayers) {
            String winPercentage = calculateWinPercentageForPlayer(jugador.getId());
            if (!winPercentage.isEmpty()) {
                // Replace commas with periods before parsing as a double
                winPercentage = winPercentage.replace(",", ".");
                double percentage = Double.parseDouble(winPercentage.replace("%", ""));
                if (percentage > bestScore) {
                    bestScore = percentage;
                    bestPlayerScore = jugador.getNom() + " " + winPercentage;
                }
            }
        }

        return bestPlayerScore;
    }

    public String getWorstScore() {
        List<Jugador> allPlayers = jugadorRepository.findAll();
        String worstPlayerScore = "";

        double worstScore = Double.MAX_VALUE;

        for (Jugador jugador : allPlayers) {
            String winPercentage = calculateWinPercentageForPlayer(jugador.getId());
            if (!winPercentage.isEmpty()) {
                // Replace commas with periods before parsing as a double
                winPercentage = winPercentage.replace(",", ".");
                double percentage = Double.parseDouble(winPercentage.replace("%", ""));
                if (percentage < worstScore) {
                    worstScore = percentage;
                    worstPlayerScore = jugador.getNom() + " " + winPercentage;
                }
            }
        }

        return worstPlayerScore;
    }

}
