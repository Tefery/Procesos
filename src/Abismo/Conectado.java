package Abismo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Conectado extends Thread {
	Socket conexCliente;
	Scanner in;
	String nombre;

	public Conectado(Socket cliente, String nombre) {
		this.conexCliente = cliente;
		this.nombre = nombre;
		in = new Scanner(System.in);
	}

	@Override
	public void run() {
		try {
			PrintStream salida = new PrintStream(conexCliente.getOutputStream(), true);

			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexCliente.getInputStream()));

			System.out.println("Introduzca el mensaje: ");

			String mensajeSalida = "";
			String mensajeEntrada = "";

			while (!mensajeSalida.equals("cerrar") && !mensajeEntrada.equals("cerrar")) {
				mensajeSalida = in.nextLine();
				salida.println(mensajeSalida);
				mensajeEntrada = entrada.readLine();
				System.out.println(mensajeEntrada);
			}

		} catch (IOException e) {
			System.out.println("Algun error ha ocurrido con el cliente " + conexCliente.getInetAddress().getHostName());
			e.printStackTrace();
		}
	}
}
