package com.sena.SpringCrudeCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.SpringCrudeCommerce.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

}
