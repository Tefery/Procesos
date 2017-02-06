package Abismo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class PadreAbsoluto {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			Runtime run = Runtime.getRuntime();
			Process proceso = run.exec("java -classpath ./bin IntervaloHijo");
			BufferedReader men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			PrintStream salida = new PrintStream(proceso.getOutputStream(), true);

			int[] intervalo = getIntervalo(men, salida);

			proceso = run.exec("java -classpath ./bin SoloPrimos2 " + intervalo[0] + " " + intervalo[1]);
			men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String mensaje = men.readLine();
			while (mensaje != null) {
				System.out.println(mensaje);
				mensaje = men.readLine();
			}
			
			proceso = run.exec("java -classpath ./bin SoloPrimos");
			men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			salida = new PrintStream(proceso.getOutputStream(), true);

			System.out.println("Numeros primos:");
			men.readLine();
			for (int i = intervalo[0]; i <= intervalo[1]; i++) {
				salida.println(i);
				mensaje = men.readLine();
				if(mensaje.startsWith("NO"))
					continue;
				else
					System.out.println(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int[] getIntervalo(BufferedReader men, PrintStream salida) throws IOException {
		String mensaje = men.readLine();
		System.out.println(mensaje);
		String sal1 = in.nextLine();
		salida.println(sal1);
		mensaje = men.readLine();
		if (mensaje.startsWith("Introduzca so")) {
			System.out.println(mensaje);
			return getIntervalo(men, salida);
		}
		System.out.println(mensaje);
		String sal2 = in.nextLine();
		salida.println(sal2);
		mensaje = men.readLine();
		if (mensaje.startsWith("Introduzca so") || mensaje.startsWith("El segundo numero")) {
			System.out.println(mensaje);
			return getIntervalo(men, salida);
		}
		int[] rango = { Integer.parseInt(sal1), Integer.parseInt(sal2) };
		return rango;
	}
}
