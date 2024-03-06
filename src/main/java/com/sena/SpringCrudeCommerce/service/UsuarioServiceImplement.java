package com.sena.SpringCrudeCommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.SpringCrudeCommerce.model.Usuario;
import com.sena.SpringCrudeCommerce.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImplement implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> get(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

}
