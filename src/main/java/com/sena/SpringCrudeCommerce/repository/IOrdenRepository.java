package com.sena.SpringCrudeCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.SpringCrudeCommerce.model.Orden;

public interface IOrdenRepository extends JpaRepository<Orden, Integer>{

}
