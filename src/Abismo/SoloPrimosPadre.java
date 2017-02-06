package Abismo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class SoloPrimosPadre {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		Runtime run = Runtime.getRuntime();
		try {
			Process proceso = run.exec("java -classpath ./bin SoloPrimos");
			BufferedReader men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			PrintStream salida = new PrintStream(proceso.getOutputStream(), true);
			
			String mensaje = men.readLine();
			System.out.println(mensaje);
			
			while (mensaje != null) {
				String numero = in.nextLine();
				salida.println(numero);
				mensaje = men.readLine();
				if(mensaje.equals("NOESPRIMO")) {
					System.out.println("No es primo");
					continue;
				}
				
				if(mensaje.equals("ERROR")) {
					System.err.println("Introduce solo numeros");
					continue;
				}
				
				System.out.println(mensaje);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		in.close();
	}
}
