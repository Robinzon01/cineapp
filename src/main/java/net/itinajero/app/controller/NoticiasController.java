package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
    
	@Autowired
	private INoticiasService serviceNoticias;
	
	@GetMapping(value = "/create")
	public String crear() {
		return "noticias/formNoticia";
	}
	
	@PostMapping(value = "/save")
	//public String guardar(@RequestParam("titulo") String titulo,@RequestParam("estatus") String estatus, @RequestParam("detalle") String detalle) {
	public String guardar(Noticia noticia) {
		
		serviceNoticias.guardar(noticia);

		return "noticias/formNoticia";
		
	}
	
	
}
