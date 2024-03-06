package com.sena.SpringCrudeCommerce.controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sena.SpringCrudeCommerce.model.DetalleOrden;
import com.sena.SpringCrudeCommerce.model.Orden;
import com.sena.SpringCrudeCommerce.model.Producto;
import com.sena.SpringCrudeCommerce.model.Usuario;
import com.sena.SpringCrudeCommerce.service.IDetalleOrdenService;
import com.sena.SpringCrudeCommerce.service.IOrdenService;
import com.sena.SpringCrudeCommerce.service.IProductoService;
import com.sena.SpringCrudeCommerce.service.IUsuarioService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/")
public class HomeUserController {

	// instancia del logger para ver datos de consola
	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(HomeUserController.class);

	// instancia del servicio producto
	@Autowired
	private IProductoService productoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrdenService ordenService;

	@Autowired
	private IDetalleOrdenService detalleOrdenService;

	// lista
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// objeto que almacena los datos de la orden
	Orden orden = new Orden();

	// metodo que mapea la vista del usuario la raiz del proyecto
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}

	// metodo para cargar el producto de usurio con el ig
	@GetMapping("productoHome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		LOGGER.info("Id producto enviado como parametro {}", id);

		// variable de la clase producto
		Producto producto = new Producto();
		// optional
		Optional<Producto> productoOptional = productoService.get(id);
		// pasar el producto
		producto = productoOptional.get();
		// enviamos con el model a la vista de productohome
		model.addAttribute("producto", producto);
		return "usuario/productoHome";
	}

	// metodo para enviar del boton productohome a carrito
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {

		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		// siempre que entra al metodo se inicia en 0
		double sumaTotal = 0;
		// variable u objeto de tipo optional para buscar el producto
		Optional<Producto> productoOptional = productoService.get(id);
		LOGGER.info("Producto añadido: {}", productoOptional.get());
		LOGGER.info("Cantidad añadida: {}", cantidad);
		// poner lo que esta en el optional
		producto = productoOptional.get();
		// poner detalles orden en cada fila o campo de la tabla
		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		Integer idProducto = producto.getId();
		Boolean insertado = detalles.stream().anyMatch(prod -> prod.getProducto().getId() == idProducto);

		if (!insertado) {
			// detalles
			detalles.add(detalleOrden);
		}

		// suma de los totales de la lista que el usuario añada al carrito
		// funcion de tipo lamda stream
		// funcion anonima dt
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "usuario/carrito";
	}

	// metodo para eliminar productos del carrito
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {
		// lista nueva de productos
		List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

		// quitar un bojeto de la lista de detalleOrden al carrito
		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNuevas.add(detalleOrden);
			}
		}
		// poniendo la nueva lista con los producto restantes del carrito
		detalles = ordenesNuevas;
		// recaulcular Productos
		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		// pasar variable a la vista
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "usuario/carrito";
	}

	@GetMapping("/getCart")
	public String getCart(Model model) {
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "usuario/carrito";
	}

	@GetMapping("/order")
	public String order(Model model) {
		Usuario usuario = usuarioService.findById(1).get();
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);
		return "/usuario/resumenOrden";
	}

	@GetMapping("/saveOrder")
	public String saveOrder() {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());
		Usuario usuario = usuarioService.findById(1).get();
		orden.setUsuario(usuario);
		ordenService.save(orden);
		// guardar los detalles de la orden
		for (DetalleOrden dt : detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);

		}
		// limpiar valores
		orden = new Orden();
		detalles.clear();
		return "redirect:/";
	}

	@PostMapping("/search")
	public String searchProducto(@RequestParam String nombre, Model model) {
		LOGGER.info("Nombre del producto: {}", nombre);
		List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre))
				.collect(Collectors.toList());
		model.addAttribute("productos", productos);
		return "usuario/home";
	}

}
