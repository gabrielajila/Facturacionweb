package edu.ucacue.infraestructura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.ucacue.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	@Query("select po from Producto po where po.nombre like %:nombre%")
	List findAllByNombre(String nombre);

}
