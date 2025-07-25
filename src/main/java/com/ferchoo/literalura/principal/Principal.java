package com.ferchoo.literalura.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ferchoo.literalura.helpers.Menu;
import com.ferchoo.literalura.service.LibroService;

@Component
public class Principal {

	Menu menu = new Menu();

	@Autowired
	LibroService service;

	public void imprimeMenu() {
		System.out.println("****************"); // TODO

		var op = -1;
		while (op != 0) {
			op = menu.menuPrincipal();
			if (op != 0) {
				seleccion(op);
			} else {
				System.out.println("****"); // TODO
			}
		}
	}

	private void seleccion(int op) {
		switch (op) {
		case 1:
			System.out.println("Nombre del libro a buscar : "); // TODO
			String palabraBusqueda = menu.leeEntrada();
			service.buscarLibroPorTitulo(palabraBusqueda);
			break;
		case 2:
			service.listarLibrosRegistrados();
			break;
		case 3:
			service.listarAutoresRegistrados();
			break;
		case 4:
			System.out.println("Digite el año: "); // TODO
			int año = menu.validaEntero(menu.leeEntrada());
			if (año != -1) {
				service.listarAutoresVivosPorAño(año);
				break;
			}
		case 5:
			service.listarIdiomas();
			String idiomas = menu.leeEntrada();
			service.listarLibrosPorIdioma(idiomas);
			break;
		
		default:
			System.out.println("Opcion invalida: " + op);
			throw new IllegalArgumentException("Unexpected value: " + op);
		}

	}

}
