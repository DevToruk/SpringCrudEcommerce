package com.sena.SpringCrudeCommerce.service;

import java.util.List;

import com.sena.SpringCrudeCommerce.model.Orden;

public interface IOrdenService {
	//metodo de guardado
	public Orden save(Orden orden);
	//lista
	public List<Orden> findAll();
	
	public String generarNumeroOrden();
}
