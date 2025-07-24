package com.ferchoo.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferchoo.literalura.model.Idioma;
import com.ferchoo.literalura.model.Persona;

public interface IdiomaRepository extends JpaRepository<Idioma, String> {

	Persona findByNombre(String nombre);

	@Query("SELECT CONCAT(i.codigo, ' - ', i.nombre) FROM Idioma i")
	List<Idioma> findIdiomasConNombre();

}
