package Abismo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		Socket conex = null;
		int puerto = 5000;
		Scanner in = null;
		try {
			conex = new Socket("localhost", puerto);
			in = new Scanner(System.in);

			System.out.println("Se ha establecido la conexion");

			// OutputStream salida = conex.getOutputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conex.getInputStream()));
			PrintStream salida = new PrintStream(conex.getOutputStream());
			System.out.println("Estoy esperando a recebir un mensaje");
			String mensRecibido = "";
			String mensEnviado = "";

			while (!mensRecibido.equals("cerrar") && !mensEnviado.equals("cerrar")) {
				mensRecibido = entrada.readLine();
				System.out.println("El mensaje recibido es: " + mensRecibido);
				mensEnviado = in.nextLine();
				salida.println(mensEnviado);
			}

		} catch (IOException e) {
			System.out.println("No se ha posido realizar la conexion con el servidor en el puerto" + puerto);
			e.printStackTrace();
		} finally {
			try {
				conex.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
