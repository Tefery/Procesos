package Abismo;
import java.util.Scanner;

public class Contrasenia {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Temporizador ControlTiempo = new Temporizador(5);
		ControlTiempo.start();
		pidecontrasenia(in, ControlTiempo);
	}

	private static void pidecontrasenia(Scanner in, Temporizador controlTiempo) {
		System.out.print("Introduce la clave de acceso, tiene " + controlTiempo.getTiempo() + " segundos: ");
		if (in.nextLine().toString().toUpperCase().equals("PEPINO")) {
			System.out.println("Bienvenido");
			// controlTiempo.finaliza();
			controlTiempo.interrupt();
			asd();
		} else {
			System.out.println("Contraseña incorrecta");
			pidecontrasenia(in, controlTiempo);
		}
	}

	private static void asd() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("hola pepino");
		}
	}
}
