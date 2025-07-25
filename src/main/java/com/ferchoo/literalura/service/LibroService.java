package com.ferchoo.literalura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferchoo.literalura.helpers.ConexionAPI;
import com.ferchoo.literalura.model.DataLibro;
import com.ferchoo.literalura.model.Libro;
import com.ferchoo.literalura.model.Persona;
import com.ferchoo.literalura.repository.IdiomaRepository;
import com.ferchoo.literalura.repository.LibroRepository;
import com.ferchoo.literalura.repository.PersonaRepository;

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
	
	public void buscarLibroPorTitulo(String nombreLibro) {
		String url = URL_BASE + "/?search=" + nombreLibro.replace(" ","%20");
		System.out.println("***"); //TODO
		System.out.println("🕒🚧");
		System.out.println("***"); //TODO
		String json = conexionAPI.getData(url);
		DataLibro data = conversor.obtenerData(json, DataLibro.class);
		System.out.println("URL: " + url );
		System.out.println("Total: " + data.total());
		System.out.println("Siguiente: " + data.linkSiguiente());
		System.out.println("Anterior: " + data.linkAnterior());
		System.out.println("***"); //TODO
		System.out.println("***"); //TODO
		
		data.resultadosLibros().forEach(datosLibro -> {
			Optional<Libro> existeLibro = libroRepository.findByLibro(datosLibro.idLibro());
			if(existeLibro.isPresent()) {
				System.out.println("****");
				System.out.printf("El libro %s ya existe", existeLibro.get().getTitulo());
				System.out.println("****");
			} else {
				Libro nuevoLibro = new Libro(datosLibro);
				nuevoLibro.setId(null);
				
				List<Persona> autoresTotal = new ArrayList<>();
				for(Persona autor : nuevoLibro.getAutores()) {
					Persona existe = personaRepository.findByNombre(autor.getNombre());
					if(existe != null) {
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
		if(libros.isEmpty()) {
			System.out.println("***");
			System.out.println(" +++ Registro de libros vacio 😒 🚧 +++");
			System.out.println("***");
		} else {
			libros.stream().forEach(l -> imprimeLibro(l));
		}
	
		return null;
	}

	public Object listarAutoresRegistrados() {
		// TODO Auto-generated method stub
		return null;
	}

	public void listarAutoresVivosPorAño(int año) {
		// TODO Auto-generated method stub		
	}

	public void listarIdiomas() {
		// TODO Auto-generated method stub
		
	}

	public void listarLibrosPorIdioma(String idioma) {
		// TODO Auto-generated method stub
		
	}
	
	private void imprimeLibro(Libro libro) {
		// TODO Auto-generated method stub
		
	}
	
}
