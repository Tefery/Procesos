package Abismo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class PadreDiviUniversal {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		iniciar(run);

	}

	private static void iniciar(Runtime run) {

		try {
			Process proceso = run.exec("java -classpath ./bin DiviUniversal");
			BufferedReader men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			PrintStream salida = new PrintStream(proceso.getOutputStream(), true);
			continuo(men, salida, proceso);
			contados(men, salida, proceso);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void continuo(BufferedReader men, PrintStream salida, Process proceso) throws IOException {
		String mensaje = men.readLine();
		System.out.println(mensaje);
		mensaje = men.readLine();
		String numero=null;
		
		while (mensaje != null) {
			System.out.println(mensaje+ " (0 para salir) ");
			numero = in.nextLine();
			if(numero.equals("0"))
				break;
			salida.println(numero);
			mensaje = men.readLine();
			while (mensaje != null && !mensaje.endsWith("ente numero: ") && !mensaje.endsWith("los divisores.")) {
				System.out.println(mensaje);
				mensaje = men.readLine();
			}
			if (mensaje.endsWith("los divisores.")) {
				System.out.println(mensaje);
				mensaje = men.readLine();
				System.out.println(mensaje);
				mensaje = men.readLine();
			}
		}
	}

	private static void contados(BufferedReader men, PrintStream salida, Process proceso) throws IOException {
		System.out.println("Cuantos numeros vas a calcular?: ");
		int cant = pedirNumero();
		String mensaje = men.readLine();
		System.out.println(mensaje);
		mensaje = men.readLine();
		for (int i = 0; i < cant; i++) {
			System.out.println(mensaje);
			salida.println(in.nextLine());
			mensaje = men.readLine();
			while (!mensaje.endsWith("s divisores.") && !mensaje.endsWith("nte numero: ")) {
				System.out.println(mensaje);
				mensaje = men.readLine();
			}
			if (mensaje.endsWith("s divisores.")) {
				System.out.println(mensaje);
				System.out.println("\tSolo se aceptan numeros.");
				mensaje = men.readLine();
				System.out.println(mensaje);
				mensaje = men.readLine();
			}
		}
	}

	private static int pedirNumero() {
		try {
			int i = in.nextInt();
			in.nextLine();
			return i;
		} catch (Exception e) {
			System.out.println("Solo se aceptan numeros que quepan en un int: ");
			in.nextLine();
			return pedirNumero();
		}
	}
}
