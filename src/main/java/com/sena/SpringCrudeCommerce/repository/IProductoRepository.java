package com.sena.SpringCrudeCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.SpringCrudeCommerce.model.Producto;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
