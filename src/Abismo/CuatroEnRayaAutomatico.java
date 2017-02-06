package Abismo;
import java.util.*;

public class CuatroEnRayaAutomatico {
	private static char VACIO = '.';
	private static char[][] tablero = new char[6][7];

	private static boolean compruebaGanador(char[][] t) {
		if (pruebaHorizontal(t) || pruebaVertical(t) || pruebaDiaDesc(t) || pruebaDiaAsc(t)) {
			return true;
		}
		return false;
	}

	private static boolean pruebaHorizontal(char[][] t) {
		int contador = 0;
		for (int i = 0; i < 6; i++) {
			contador = 0;
			for (int j = 0; j < 7 - 1; j++) {
				if (t[i][j] == t[i][j + 1] && t[i][j] != VACIO) {
					contador += 1;
				} else {
					contador = 0;
				}
				if (contador == 3) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean pruebaVertical(char[][] t) {
		int contador = 0;
		for (int i = 0; i < 7; i++) {
			contador = 0;
			for (int j = 0; j < 6 - 1; j++) {
				if (t[j][i] == t[j + 1][i] && t[j][i] != VACIO) {
					contador += 1;
				} else {
					contador = 0;
				}
				if (contador == 3) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean pruebaDiaDesc(char[][] t) {
		for (int i = 0; i < 6 - 3; i++) {
			for (int j = 0; j < 7 - 3; j++){
				for( int c = 0 ; t[i + c][j + c] == t[i + 1 + c][j + 1 + c] && t[i][j] != VACIO ; c++){
					if(c >= 2){
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean pruebaDiaAsc(char[][] t) {
		for (int i = 5; i > 0 + 2; i--) {
			for (int j = 0; j < 7 - 3; j++){
				for( int c = 0 ; t[i - c][j + c] == t[i - ( 1 + c )][j + 1 + c] && t[i][j] != VACIO ; c++){
					if(c >= 2){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static void fichaDeJugador(int t, Scanner In) {

		if (t % 2 == 1) {
			System.out.println("Jugador 1: que columna?");
		} else {
			System.out.println("Jugador 2: que columna?");
		}
		meteFicha(t, Integer.parseInt(In.nextLine()), In);
	}

	private static int primeraFilaLibre(int c) {
		for (int i = 5; i >= 0; i--) {
			if (tablero[i][c - 1] == '.') {
				return i;
			}
		}
		return -1;
	}

	private static void meteFicha(int t, int c, Scanner In) {
		if (c <= 0 || c > 7) {
			System.out.println("Columna invalida");
			muestraTablero();
			fichaDeJugador(t, In);
		} else {
			int ficha = primeraFilaLibre(c);
			if (ficha == -1) {
				System.out.println("Columna invalida");
				muestraTablero();
				fichaDeJugador(t, In);
			} else {
				if (t % 2 == 1) {
					tablero[ficha][c - 1] = 'O';
				} else {
					tablero[ficha][c - 1] = 'X';
				}
			}
		}
	}

	private static boolean tableroLleno() {
		for (int v = 0; v < 6; v++) {
			for (int h = 0; h < 7; h++) {
				if (tablero[v][h] == VACIO) {
					return false;
				}
			}
		}
		return true;
	}

	private static void muestraTablero() {
		for (int v = 0; v < 6; v++) {
			for (int h = 0; h < 7; h++) {
				System.out.print(tablero[v][h] + " ");
			}
			System.out.println();
		}

	}

	private static void vaciaTablero() {
		for (int v = 0; v < 6; v++) {
			for (int h = 0; h < 7; h++) {
				tablero[v][h] = VACIO;
			}
		}
	}

	public static void main(String[] args) {

		Scanner In = new Scanner(System.in);

		vaciaTablero();

		int turno = 1;
		boolean TableroLleno = false;
		boolean Ganador = false;

		while (TableroLleno != true && Ganador != true) {
			muestraTablero();
			fichaDeJugador(turno, In);
			TableroLleno = tableroLleno();
			Ganador = compruebaGanador(tablero);
			turno += 1;
		}
		if (Ganador == true) {
			muestraTablero();
			if ((turno - 1) % 2 == 1) {
				System.out.println("Gana el jugador 1");
			} else {
				System.out.println("Gana el jugador 2");
			}
		} else if (TableroLleno == true) {
			muestraTablero();
			System.out.println("Empate");
		}
	}
}
