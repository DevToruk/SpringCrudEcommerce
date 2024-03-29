package com.sena.SpringCrudeCommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.SpringCrudeCommerce.model.Producto;
import com.sena.SpringCrudeCommerce.repository.IProductoRepository;

@Service
public class ProductoServiceImplement implements IProductoService {
	
	@Autowired
	private IProductoRepository productorepository;
	
	@Override
	public Producto save(Producto producto) {
		return productorepository.save(producto);
	}
	
	@Override
	public Optional<Producto> get(Integer id){
		return productorepository.findById(id);
	}
	
	@Override
	public void update(Producto producto) {
	productorepository.save(producto);
	}
	
	@Override
	public void delete(Integer id) {
		productorepository.deleteById(id);
	}
	
	@Override
	public List<Producto> findAll(){
		return productorepository.findAll();
	}
	
	
}
