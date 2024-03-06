package com.sena.SpringCrudeCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.SpringCrudeCommerce.model.DetalleOrden;
import com.sena.SpringCrudeCommerce.repository.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImplement implements IDetalleOrdenService{

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepositoy;
	
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		// TODO Auto-generated method stub
		return detalleOrdenRepositoy.save(detalleOrden);
	}

}
