package com.ferchoo.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataPersona(
			@JsonAlias("name")
			String nombre,
			@JsonAlias("birth_year")
			Integer añoNacimiento,
			@JsonAlias("death_year")
			Integer añoDefuncion			
		) {	
}
