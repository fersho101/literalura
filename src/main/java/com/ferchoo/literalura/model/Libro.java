package com.ferchoo.literalura.model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "libros")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private Long idLibro;

	@Column(unique = true, length = 250)
	private String titulo;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "libro-idioma", joinColumns = @JoinColumn(name = "id_libro"), inverseJoinColumns = @JoinColumn(name = "codigo"))
	private List<Idioma> idiomas;

	private Long descargas;
	private boolean copyright;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "persona_libro", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "persona_id"))
	private List<Persona> autores;

	public Libro() {
		super();
	}

	public Libro(DataResultadoLibro libro) {
		this.idLibro = libro.idLibro();
		this.titulo = (libro.titulo().length() > 250 ? libro.titulo().substring(0, 249) : libro.titulo());
		this.idiomas = libro.idiomas().stream().map(i -> new Idioma(i, null)).collect(Collectors.toUnmodifiableList());
		this.descargas = libro.descargas();
		this.autores = libro.autores()
				.stream()
				.map(a -> new Persona(a)).collect(Collectors.toList());
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public Long getDescargas() {
		return descargas;
	}

	public void setDescargas(Long descargas) {
		this.descargas = descargas;
	}

	public boolean isCopyright() {
		return copyright;
	}

	public void setCopyright(boolean copyright) {
		this.copyright = copyright;
	}

	public List<Persona> getAutores() {
		return autores;
	}

	public void setAutores(List<Persona> autores) {
		List<Libro> libros = autores.stream().map(
				Libro -> this).collect(Collectors.toList());
		autores.forEach(p -> p.setLibros(libros));
		this.autores = autores;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", idiomas=" + idiomas + ", descargas=" + descargas
				+ ", copyright=" + copyright + ", autores=" + autores + "]";
	}
	
	

}
