package Abismo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class ExamenPadre {

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			Runtime run = null;
			run = Runtime.getRuntime();
			System.out.println(
					"Escriba 1 si quiere trabajar con la frase, cualquier otra cosa para mostrar el abecedario");
			String opcion = in.nextLine().replace(" ", "");
			Process proceso = null;
			try {
				proceso = run.exec("java -classpath ./bin ExaamenPrueba " + opcion);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}

			BufferedReader men = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
			String mensaje = men.readLine();
			if(mensaje == null){
			BufferedReader mena = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
			String mensajea = mena.readLine();
			System.err.println("No se ha podido ejecutar el programa “Hijo”. Esta es la información que nos da el SO: …");
			while (mensajea != null) {
				System.err.println(mensajea);
				mensajea = mena.readLine();
			}
			System.exit(0);
			}
			try {
				PrintStream salida = new PrintStream(proceso.getOutputStream(), true);
				opcion = "S";
				if (mensaje.endsWith("frase: ")) {
					while (mensaje != null && opcion.equals("S")) {
						System.out.println(mensaje);
						opcion = in.nextLine();
						salida.println(opcion);
						mensaje = men.readLine();
						System.out.println(mensaje);
						mensaje = men.readLine();
						System.out.println(mensaje);
						mensaje = men.readLine();
						System.out.println(mensaje);
						mensaje = men.readLine();
						System.out.println(mensaje);
						opcion = in.nextLine();
						salida.println(opcion);
						mensaje = men.readLine();
						System.out.println(mensaje);
						System.out.println("\t\tQuieres insertar otra frase? S/N");
						opcion = in.nextLine().toUpperCase();
						mensaje = men.readLine();
					}
				} else {
					while (mensaje != null) {
						System.out.println(mensaje);
						mensaje = men.readLine();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (VirtualMachineError e) {
			System.err.println("Ha habido algún error al ejecutar la máquina virtual de Java");
			System.exit(0);
		}
	}

}