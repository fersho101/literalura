package com.ferchoo.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataResultadoLibro(
			@JsonAlias("id") Long idLibro,
			@JsonAlias("title") String titulo,
			@JsonAlias("authors") List<DataPersona> autores,
			@JsonAlias("summaries") List<String> resumen,
			@JsonAlias("translators") List<DataPersona> traductores,
		/* @JsonAlias("bookshelves")List<String> estanteria, */
			@JsonAlias("languages") List<String> idiomas,
			@JsonAlias("copyright") Boolean copyright,						
			@JsonAlias("media_type") String tipoMedio,
			@JsonAlias("download_count") Long descargas		
		) {
}
