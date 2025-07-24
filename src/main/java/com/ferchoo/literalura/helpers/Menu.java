package com.ferchoo.literalura.helpers;

import java.util.Scanner;

public class Menu {

	private Scanner sc = new Scanner(System.in);

	public String leeEntrada() {
		return sc.nextLine();
	}

	public int menuPrincipal() {

		var menu = """
				1. Buscar Libro por titulo.
				2. Listar libros registrados.
				3. Listar autores registrados.
				4. Listar autores vivos en x año.
				5.Listar libros por idioma.
				6. Listar libros por idioma paginado.

				0. Salir
				""";

		int opcion = -1;
		while (opcion == -1) {
			System.out.println("===========================");
			System.out.println("Menu Principal");
			System.out.println("===========================");
			System.out.println(menu);

			opcion = validaEntero(leeEntrada());
			if (opcion < 0 || opcion > 6) {
				opcion = -1;
				System.out.println("Opcion invalida");
				System.err.println("***");
			}
		}
		return opcion;
	}

	public Integer validaEntero(String v) {
		Integer valor;
		try {
			valor = Integer.valueOf(v);
		} catch (NumberFormatException e) {
			valor = -1;
			System.out.println("Opcion invalida");
			System.out.println("*");
		}
		return valor;
	}

}
