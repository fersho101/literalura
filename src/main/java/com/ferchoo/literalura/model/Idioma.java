package com.ferchoo.literalura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "idiomas")
public class Idioma {
	@Id
	private String codigo;
	private String nombre;

	public Idioma() {
		super();
	}

	public Idioma(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Idioma [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
