package Abismo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class NumerosPrimosFinal {
	private static String mensaje;

	public static void main(String[] args) {

		Runtime run = Runtime.getRuntime();
		Scanner in = new Scanner(System.in);
		iniciar(run, in);
		in.close();
	}

	private static void iniciar(Runtime run, Scanner in) {
		try {
			Process proceso = run.exec("java -classpath ./bin NumerosPrimos");
			BufferedReader men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			OutputStream out = proceso.getOutputStream();
			PrintStream salida = new PrintStream(out, true);
			mensaje = men.readLine();

			do {
				System.out.println(mensaje);
				salida.println(in.nextLine());
				mensaje = men.readLine();

				while (!mensaje.equals("$ERROR") && mensaje != null) {
					System.out.println(mensaje);
					mensaje = men.readLine();
				}

				mensaje = men.readLine();
			} while (mensaje != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
