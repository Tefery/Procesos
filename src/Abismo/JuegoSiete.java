package Abismo;
import java.util.ArrayList;
import java.util.Scanner;

public class JuegoSiete {
	public static void main(String[] args) {
		System.out.println("Cuantos jugadores van a participar? ");
		Scanner in = new Scanner(System.in);
		String canjugadores = in.nextLine();

		Siete jug1 = new Siete();
		ArrayList<Siete> jugadores = new ArrayList<Siete>();

		for (int i = 1; i <= Integer.parseInt(canjugadores); i++) {
			jug1 = new Siete(Colores.GREEN, 5, "Jugador "+i);
			jugadores.add(jug1);
		}

		jug1.setOtrosJugadores(jugadores);

		jug1.start();
		in.close();
	}
}