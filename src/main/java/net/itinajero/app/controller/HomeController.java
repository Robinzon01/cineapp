package net.itinajero.app.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {
    @Autowired
	private IPeliculasService servicePelicualas;
	
    private  SimpleDateFormat dateForma = new SimpleDateFormat("dd-MM-yyyy");
  
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String buscar(@RequestParam("fecha") String fecha,Model model) {
		System.out.println("Buscamos todas las peliculas a la fecha : "+fecha);
		
        List<Pelicula> peliculas = servicePelicualas.buscarTodas();
		
		List<String> listaFechas = Utileria.getNextDays(10);

		model.addAttribute("fechas",listaFechas);
		model.addAttribute("peliculas", peliculas);
        model.addAttribute("fechaBusqueda",fecha );
		
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
 	public String mostrarPrincipal(Model model) {

		List<Pelicula> peliculas = servicePelicualas.buscarTodas();
		
		List<String> listaFechas = Utileria.getNextDays(10);

		model.addAttribute("fechas",listaFechas);
		model.addAttribute("peliculas", peliculas);
        model.addAttribute("fechaBusqueda", dateForma.format(new Date()) );
		return "home";
		
	}

	//@RequestMapping(value = "/detail/{id}/{fecha}",method = RequestMethod.GET)
	@RequestMapping(value = "/detail",method = RequestMethod.GET)
	//public String mostrarDetalle(Model model,@PathVariable("id") int idPelicula,@PathVariable("fecha") String fecha2)
	public String mostrarDetalle(Model model,@RequestParam("idMovie") int idPelicula,@RequestParam("fecha") String fecha2) {
		
		System.out.println("Id de pelicula : "+idPelicula);
		System.out.println("Fecha : "+fecha2);
		
		model.addAttribute("pelicula", servicePelicualas.buscarPorId(idPelicula));
		
		return "detalle";
	}


}
