package com.sena.SpringCrudeCommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.SpringCrudeCommerce.model.Orden;
import com.sena.SpringCrudeCommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImplement implements IOrdenService{

	@Autowired
	private IOrdenRepository OrdenRepository;
	
	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return OrdenRepository.save(orden);
	}

	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return OrdenRepository.findAll();
	}

	@Override
	public String generarNumeroOrden() {
		// Un objeto de tipo entero para el incremento y luego pasarlo a string
		int numero = 0;
		// El objeto con el número secuencial
		String numeroOrden ="";
		//Lista de ordenes
		List<Orden> ordenes = findAll();
		//Lista de enteros para el incremento
		List<Integer> numeros = new ArrayList<Integer>();
		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
		
		if (ordenes.isEmpty()) {
			numero = numeros.stream().max(Integer::compare).get();
			numero++;
		}
		
		if (numero < 10) {
			numeroOrden = "00000000" + String.valueOf(numero);
		} else if(numero < 100){
			numeroOrden = "0000000" + String.valueOf(numero);
		} else if(numero < 1000){
			numeroOrden = "000000" + String.valueOf(numero);
		} else if(numero < 10000){
			numeroOrden = "00000" + String.valueOf(numero);
		}
		
		return numeroOrden;
	}
	
}