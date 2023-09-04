package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.services.JugadorServei;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.services.PartidaService;

@Controller
@RequestMapping("/players")
public class JugadorController {
	
	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private JugadorServei jugadorServei;
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@Autowired
	private PartidaService partidaService;
	
	@GetMapping({"/getAll"})
    public ModelAndView listPlayers() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("jugadors", jugadorRepository.findAll());
        return mav;
    }
	
	@GetMapping({"/ranking"})
    public ModelAndView Rankings() {
        ModelAndView mav = new ModelAndView("rankings");
        mav.addObject("jugadors", jugadorRepository.findAll());
        //Creem una llista per veure el percentatge d'èxit i agafar el percentatge mitjà d'èxit, el millor i el pitjor
        List<Partida> playerGames = partidaRepository.findAll();

        int totalPartides = playerGames.size();
        int guanyades = 0;

        for (Partida partida : playerGames) {
            if (partida.isGuanyada()) {
                guanyades++;
            } 
        }

        double mitjanaGuanyades = (guanyades * 100.0) / totalPartides;
        String mitjanaGuanyadesFormatted = String.format("%.2f", mitjanaGuanyades) + "%";
        mav.addObject("mitjanaGuanyades", mitjanaGuanyadesFormatted);
        
        //Fem una llista amb els percentatgews mitjans d'epxit de cada jugador
        List<Jugador> players = jugadorRepository.findAll();

        // Extract player IDs from the list
        List<Integer> playerIds = players.stream()
                .map(Jugador::getId)
                .collect(Collectors.toList());
        //List<Integer> playerIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9); // Replace with your list of player IDs
        List<String> winPercentages = partidaService.calculateWinPercentagesForPlayers(playerIds);

        mav.addObject("winPercentages", winPercentages);
        
        
        return mav;
    }
	
	@GetMapping("/ranking/winner")
	public ModelAndView bestPlayer() {
	    ModelAndView mav = new ModelAndView("millorJugador");
	    
	    String bestScore = partidaService.getBestScore();
	    
	    mav.addObject("millorJugador", bestScore);
	    
	    return mav;
	}

	@GetMapping("/ranking/loser")
	public ModelAndView worstPlayer() {
	    ModelAndView mav = new ModelAndView("pitjorJugador");
	    
	    String worstScore = partidaService.getWorstScore();
	    
	    mav.addObject("pitjorJugador", worstScore);
	    
	    return mav;
	}

	
	
	@GetMapping("/add")
	public ModelAndView afegirJugador() {
		ModelAndView mav = new ModelAndView("afegirJugador");
		Jugador nouJugador = new Jugador();
		mav.addObject("jugador", nouJugador);
		return mav;
	}
	
	@PostMapping("/save")
    public String saveJugador(@ModelAttribute Jugador jugador) {
        jugadorServei.crearJugador(jugador);
        return "redirect:/players/" + jugador.getId() + "/games";
    }
	
	@PostMapping("/savePartida")
    public String savePartida(@ModelAttribute Partida partida) {
		System.out.println("jugadorId: " + partida.getJugadorId());
	    System.out.println("valorDau1: " + partida.getValorDau1());
	    System.out.println("valorDau2: " + partida.getValorDau2());
	    System.out.println("resultat: " + partida.getResultat());
	    System.out.println("guanyada: " + partida.isGuanyada());
        jugadorServei.crearPartida(partida);
        return "redirect:/players/" + partida.getJugadorId() + "/games";
    }
	 
	@GetMapping("/update/{id}")
	public ModelAndView updateJugadorForm(@PathVariable Integer id) {
	    ModelAndView mav = new ModelAndView("canviarNom");
	    Jugador jugador = jugadorRepository.findById(id).get();
	    mav.addObject("jugador", jugador);
	    return mav;
	}
		 
	 @GetMapping("/{id}/jugar")
	 public ModelAndView mostrarVistaPartidaDaus(@PathVariable Integer id) {
	     ModelAndView mav = new ModelAndView("partidaDaus");
	     mav.addObject("jugadorIdObtain", id);
	     System.out.println("Valor de id: " + id);
	     mav.addObject("missatge", "Tira els daus!");
	     Partida novaPartida = new Partida();
	     mav.addObject("partida", novaPartida);
	     return mav;
	 }
	 
	 
	 @GetMapping("/{id}/games")
	 public ModelAndView listPlayerGames(@PathVariable Integer id) {
	     ModelAndView mav = new ModelAndView("partides");
	     
	     // Fetch the player's name by their ID (assuming you have a JugadorRepository)
	     String jugadorNom = jugadorRepository.findById(id)
	             .map(Jugador::getNom) // Use map to extract the name if the player exists
	             .orElse("Jugador not found"); // Default value if the player is not found

	     mav.addObject("jugadorNom", jugadorNom);
	      

	     mav.addObject("jugadorIdObtain", id);
	     
	     List<Partida> playerGames = partidaRepository.findByJugadorId(id);
	     mav.addObject("partides", playerGames);
	     
	     
	     String percentatgeGuanyades = partidaService.calculateWinPercentageForPlayer(id);
	     mav.addObject("percentatge", percentatgeGuanyades);
         return mav;
	 }
	 
	 @GetMapping("/{id}/deleteGames")
	 public String deleteAllPlayerGames(@PathVariable Integer id) {
	     // Fetch all Partida records associated with the player
	     List<Partida> playerGames = partidaRepository.findByJugadorId(id);

	     // Delete all the fetched Partida records
	     partidaRepository.deleteAll(playerGames);

	     // Redirect to the player's game list page or any other appropriate page
	     return "redirect:/players/" + id + "/games";
	 }


	 
	 

	   
	 
	 
	 

	 
}
