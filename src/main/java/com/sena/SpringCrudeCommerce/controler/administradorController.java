package com.sena.SpringCrudeCommerce.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.SpringCrudeCommerce.model.Producto;
import com.sena.SpringCrudeCommerce.service.ProductoService;

@Controller
@RequestMapping("/administrador")
public class administradorController {
	
	@Autowired
	private ProductoService ProductoService;

	@GetMapping("")
	public String home(Model model) {
		
		List<Producto> productos = ProductoService.findAll();
		model.addAttribute("productos", productos);
		
		return "administrador/home";
	}
}
