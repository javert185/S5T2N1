package cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cat.itacademy.barcelonactiva.carapuigmasnou.sergi.s05.t02.n01.model.repository.PartidaRepository;

@Controller
@RequestMapping("/games")
public class PartidaController {
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@GetMapping({"/"})
    public ModelAndView listPlayers() {
        ModelAndView mav = new ModelAndView("partides");
        mav.addObject("partides", partidaRepository.findAll());
        return mav;
    }

}
