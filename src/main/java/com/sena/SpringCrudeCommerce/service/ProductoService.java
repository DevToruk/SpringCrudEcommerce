package com.sena.SpringCrudeCommerce.service;

import java.util.List;
import java.util.Optional;

import com.sena.SpringCrudeCommerce.model.Producto;

public interface ProductoService {
	public Producto save(Producto producto);// create

	public Optional<Producto> get(Integer id);// read

	public void update(Producto producto);

	public void delete(Integer id);

	// listas tables
	public List<Producto> findAll();
}
