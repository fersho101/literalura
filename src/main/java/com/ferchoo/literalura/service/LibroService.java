package com.ferchoo.literalura.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferchoo.literalura.helpers.ConexionAPI;
import com.ferchoo.literalura.model.DataLibro;
import com.ferchoo.literalura.model.Idioma;
import com.ferchoo.literalura.model.Libro;
import com.ferchoo.literalura.model.Persona;
import com.ferchoo.literalura.repository.IdiomaRepository;
import com.ferchoo.literalura.repository.LibroRepository;
import com.ferchoo.literalura.repository.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class LibroService {

	private final String URL_BASE = "https://gutendex.com/books";

	@Autowired
	ConexionAPI conexionAPI;

	@Autowired
	LibroRepository libroRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	IdiomaRepository idiomaRepository;

	private ConvierteDataServiceImpl conversor = new ConvierteDataServiceImpl();	

	public LibroService() {
		super();
	}

	@Transactional
	public void buscarLibroPorTitulo(String nombreLibro) {
		String url = URL_BASE + "/?search=" + nombreLibro.replace(" ", "%20");
		System.out.println("***"); // TODO
		System.out.println("🕒🚧");
		System.out.println("***"); // TODO
		String json = conexionAPI.getData(url);
		DataLibro data = conversor.obtenerData(json, DataLibro.class);
		System.out.println("URL: " + url);
		System.out.println("Total: " + data.total());
		System.out.println("Siguiente: " + data.linkSiguiente());
		System.out.println("Anterior: " + data.linkAnterior());
		System.out.println("***"); // TODO
		System.out.println("***"); // TODO

		data.resultadosLibros().forEach(datosLibro -> {
			Optional<Libro> existeLibro = libroRepository.findByLibro(datosLibro.idLibro());
			if (existeLibro.isPresent()) {
				System.out.println("****");
				System.out.printf("El libro %s ya existe", existeLibro.get().getTitulo());
				System.out.println("****");
			} else {
				Libro nuevoLibro = new Libro(datosLibro);
				nuevoLibro.setId(null);

				List<Persona> autoresTotal = new ArrayList<>();
				for (Persona autor : nuevoLibro.getAutores()) {
					Persona existe = personaRepository.findByNombre(autor.getNombre());
					if (existe != null) {
						existe.getLibros().add(nuevoLibro);
						autoresTotal.add(existe);
					} else {
						autor.setLibros(List.of(nuevoLibro));
						autoresTotal.add(autor);
					}
				}

				nuevoLibro.setAutores(autoresTotal);
				libroRepository.save(nuevoLibro);
				imprimeLibro(nuevoLibro);
			}
		});

	}

	public Object listarLibrosRegistrados() {
		List<Libro> libros = libroRepository.findAll();
		if (libros.isEmpty()) {
			System.out.println("***");
			System.out.println(" +++ Registro de libros vacio 😒 🚧 +++");
			System.out.println("***");
		} else {
			libros.stream().forEach(l -> imprimeLibro(l));
		}

		return null;
	}

	public void listarAutoresRegistrados() {
		List<Persona> autores = personaRepository.findAll();
		if (autores.isEmpty()) {
			System.out.println("***");
			System.out.println(" +++ Registro de autores vacio 😒 🚧 +++");
			System.out.println("***");
		} else {
			autores.stream().forEach(a -> imprimeAutor(a));
		}
	}

	public void listarAutoresVivosPorAño(int año) {
		List<Persona> autores = personaRepository.findByAñoAutor(año);
		if (autores.isEmpty()) {
			System.out.println("***");
			System.out.printf(" +++ Registro de autores vacio en el año %d 😒 🚧 +++\n", año);
			System.out.println("***");
		} else {
			autores.stream().forEach(a -> imprimeAutor(a));
		}
	}

	public boolean listarIdiomas() {
		List<Idioma> idiomas = idiomaRepository.findAll();
		if (idiomas.isEmpty()) {
			System.out.println("***");
			System.out.printf(" +++ Registro de idiomas vacio 😒 🚧 +++");
			System.out.println("***");
			return false;
		}

		System.out.println("***");
		System.out.printf("Registro de idiomas disponibles:  ");
		System.out.printf("Registro de idiomas disponibles:  ");
		int cont = 0;
		for (Idioma idioma : idiomas) {
			System.out.printf("%-25s", idioma.toString());
			cont++;
			if (cont % 5 == 0) {
				System.out.println();
			}
		}
		if (cont % 5 != 0) {
			System.out.println();
		}
		return true;
	}

	public void listarLibrosPorIdioma(String idiomas) {
		List<String> codigos = Arrays.stream(idiomas.split(",")).map(String::trim).toList();
		List<Libro> libros = libroRepository.findByIdiomas(codigos);

		if (libros.isEmpty()) {
			System.out.println("***");
			System.out.printf(" +++ No existen libro registrados en idioma seleccionado😒 🚧 +++");
			System.out.println("***");
		} else {
			libros.stream().forEach(l -> imprimeLibro(l));
		}

	}

	private void imprimeLibro(Libro libro) {
		String datosLibro = "\nTitulo:  " + libro.getTitulo() + "\nAutor(es): " + imprimeAutores(libro.getAutores())
				+ "\nIdioma(s): " + libro.getIdiomas() + "\nTotal descargas: " + libro.getDescargas();
		System.out.println(datosLibro);
	}

	private String imprimeAutores(List<Persona> autores) {
		if (autores == null || autores.isEmpty())
			return "";
		if (autores.size() == 1)
			return autores.get(0).getNombre();

		StringBuilder noms = new StringBuilder("[ ");
		for (Persona autor : autores) {
			noms.append(autor.getNombre());
		}
		noms.append(" ]");
		return noms.toString();
	}

	private void imprimeAutor(Persona autor) {
		String datosAutor = "Autor: " + autor.getNombre() + "\nFecha de nacimiento: " + autor.getAñoNacimiento()
				+ "\nFecha de defunción: " + autor.getAñoDefuncion() + "\nLibro(s): "
				+ imprimeLibros(autor.getLibros());
		System.out.println(datosAutor);
	}

	private String imprimeLibros(List<Libro> libros) {
		if (libros == null || libros.isEmpty())
			return "";
		if (libros.size() == 1)
			return libros.get(0).getTitulo();

		StringBuilder titulo = new StringBuilder("[ ");
		for (Libro elem : libros) {
			titulo.append(elem.getTitulo());
		}
		titulo.append(" ]");
		return titulo.toString();
	}

}
