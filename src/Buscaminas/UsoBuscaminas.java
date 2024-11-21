package Buscaminas;

import java.util.Scanner;

public class UsoBuscaminas {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int opcion = menu();
		JuegoBuscaminas tablero = new JuegoBuscaminas(opcion);
		System.out.println("\nAsí se selecciona una casilla:\n" + "\"m fila,columna\" para marcar con bandera \n"
				+ "\"d fila,columna\" para descubrir una casilla");
		int fila = 0, columna = 0, minasQueQuedan = 0;
		String modo;
		tablero.iniciarTablero();
		do {
			tablero.imprimirTablero();
			do {
				System.out.println("\nSelecciona una casilla de la manera explicada");
				String seleccion = in.nextLine();
				String[] partes = seleccion.split(" ");
				modo = partes[0];
				String[] filaYColumna = partes[1].split(",");
				fila = Integer.parseInt(filaYColumna[0]) - 1;
				columna = Integer.parseInt(filaYColumna[1]) - 1;
			} while (!tablero.movimientoValido(opcion, fila, columna));

			switch (modo) {
			case "d":
				tablero.descubrirCasilla(fila, columna);
				break;
			case "m":
				tablero.marcarCasilla(fila, columna);
				minasQueQuedan = tablero.getMinasPorNivel() - tablero.getMinasDescubiertas();
				System.out.println("Llevas: " + tablero.getMinasDescubiertas() + " minas");
				System.out.println("Te quedan: " + minasQueQuedan + " minas");
				break;
			default:
				System.out.println("Error. La letra tiene que ser \"d\" o \"m\" ");
			}

		} while (tablero.causaTerminacionJuego(opcion, fila, columna) == 0);

		switch (tablero.causaTerminacionJuego(opcion, fila, columna)) {
		case 1:
			System.out.println("\nEnhorabuena, has ganado!!");
			break;
		case 2:
			System.out.println("\n¡AY CARAMBA! Ha explotado una mina");
			break;
		case 3:
			System.out.println("\nVaya hombre, has marcado una casilla en la que " + "no había mina");
			break;
		case 4:
			System.out.println("\n¡Enhorabuena! Has encontrado todas las minas");
			break;
		}
	}

	public static int menu() {
		System.out.println("  ____                                _                 \r\n"
				+ " |  _ \\                              (_)                \r\n"
				+ " | |_) |_   _ ___  ___ __ _ _ __ ___  _ _ __   __ _ ___ \r\n"
				+ " |  _ <| | | / __|/ __/ _` | '_ ` _ \\| | '_ \\ / _` / __|\r\n"
				+ " | |_) | |_| \\__ \\ (_| (_| | | | | | | | | | | (_| \\__ \\\r\n"
				+ " |____/ \\__,_|___/\\___\\__,_|_| |_| |_|_|_| |_|\\__,_|___/\r\n"
				+ "                                                        ");
		System.out.println(
				"\nNiveles de dificultad:\n" + "1. Nivel Principiante\n" + "2. Nivel Amateur\n" + "3. Nivel flipado\n");
		while (!in.hasNextInt()) {
			System.out.println("\nError. No has introducido una opción válida");
			in.nextLine();
			System.out.println("\nIntroduce otra opción");
		}
		int opcion = in.nextInt();
		while (opcion < 0 || opcion > 3) {
			System.out.println("\nError. No has introducido una opción válida");
			System.out.println("\nIntroduce otra opción");
			opcion = in.nextInt();
		}
		in.nextLine();
		return opcion;
	}
}
