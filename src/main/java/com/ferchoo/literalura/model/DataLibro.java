package com.ferchoo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataLibro(
			@JsonAlias("count") Integer total, 
			@JsonAlias("next") String linkSiguiente,
			@JsonAlias("previous") String linkAnterior, 
			@JsonAlias("results") List<DataResultadoLibro> resultadosLibros			
		) {
}
