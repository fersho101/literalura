package com.ferchoo.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferchoo.literalura.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
	Persona findByNombre(String nombre);

	@Query("SELECT p FROM Persona p LEFT JOIN FETCH p.libros WHERE p.añoDefuncion >= :año And p.añoNacimiento <= año")
	List<Persona> findByAñoAutor(@Param("año") Integer year);
}
