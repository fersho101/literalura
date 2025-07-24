package com.ferchoo.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferchoo.literalura.helpers.ConexionAPI;
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
}
