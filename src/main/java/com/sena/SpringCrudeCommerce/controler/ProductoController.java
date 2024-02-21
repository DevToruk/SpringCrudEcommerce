package com.sena.SpringCrudeCommerce.controler;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.SpringCrudeCommerce.model.Producto;
import com.sena.SpringCrudeCommerce.model.Usuario;
import com.sena.SpringCrudeCommerce.service.ProductoService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	//variable para la carga de imagenes
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto) {
		LOGGER.info("este es el producto a guardar en la BD {}", producto);
		Usuario u = new Usuario(1, "", "", "", "", "", "", "", "");
		producto.setUsuario(u);
		productoService.save(producto);
		return "redirect:/productos";
	}
	
	

}
