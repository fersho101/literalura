package com.ferchoo.literalura.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "personas")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLong;

	@Column(length = 250)
	private String nombre;
	private Integer añoNacimiento;
	private Integer añoDefuncion;

	@ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
	private List<Libro> libros;

	public Persona() {
		super();
	}

	public Persona(DataPersona persona) {
		this.nombre = (persona.nombre().length() > 250 ? persona.nombre().substring(0, 249) : persona.nombre());
		this.añoNacimiento = persona.añoNacimiento();
		this.añoDefuncion = persona.añoDefuncion();
	}
	
	

	public Long getIdLong() {
		return idLong;
	}

	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAñoNacimiento() {
		return añoNacimiento;
	}

	public void setAñoNacimiento(Integer añoNacimiento) {
		this.añoNacimiento = añoNacimiento;
	}

	public Integer getAñoDefuncion() {
		return añoDefuncion;
	}

	public void setAñoDefuncion(Integer añoDefuncion) {
		this.añoDefuncion = añoDefuncion;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", añoNacimiento=" + añoNacimiento + ", añoDefuncion=" + añoDefuncion
				+ ", libros=" + libros + "]";
	}

}
