package com.sena.SpringCrudeCommerce.service;

import java.util.Optional;

import com.sena.SpringCrudeCommerce.model.Usuario;

public interface IUsuarioService {
	
	public Usuario save(Usuario usuario);
	
	public Optional<Usuario> get(Integer id);
	
	public void update(Usuario usuario);
	
	public void delete(Integer id);
	
	Optional<Usuario> findById(Integer id);
}
