package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Jugador;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.services.JugadorServei;

@Controller
@RequestMapping("/players")
public class JugadorController {
	
	@Autowired
	private JugadorRepository jugadorRepository;
	
	@Autowired
	private JugadorServei jugadorServei;
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@GetMapping({"/getAll"})
    public ModelAndView listPlayers() {
        ModelAndView mav = new ModelAndView("players");
        mav.addObject("jugadors", jugadorRepository.findAll());
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
        return "redirect:/players/getAll";
    }
	 
	 @GetMapping("/update")
		public ModelAndView updateJugadorForm(@RequestParam Integer id) {
			ModelAndView mav = new ModelAndView("canviarNom");
			Jugador jugador = jugadorRepository.findById(id).get();
			mav.addObject("jugador", jugador);
			return mav;
		}
	 
	 @GetMapping("/jugar")
	 public ModelAndView mostrarVistaPartidaDaus() {
	     ModelAndView mav = new ModelAndView("partidaDaus");
	     mav.addObject("missatge", "Tira els daus!");
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
