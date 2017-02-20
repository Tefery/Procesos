package apuntes;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class HiloEnviaCliente extends Thread {

	String mensaje;
	PrintStream salida;
	Socket conexion;

	public HiloEnviaCliente(Socket conexion) {
		this.conexion = conexion;
		this.setDaemon(true);
		this.start();
	}

	@Override
	public void run() {
			Scanner in = new Scanner(System.in);
		try {
			salida = new PrintStream(conexion.getOutputStream(), true);
			System.out.print("Introduzca el nombre de usuario: ");
			mensaje = in.nextLine();
			salida.println(mensaje);
			System.out.println("Conexion establecida como "+mensaje);
			mensaje = in.nextLine();
			salida.println(mensaje);
			while (mensaje.contains(":")) {
				mensaje = in.nextLine();
				salida.println(mensaje);
			}
			conexion.close();
		} catch (IOException ex) {
			System.err.println("Ha ocurrido un error inesperado en la clase HiloEnviaCliente");
			ex.printStackTrace();
		}

	}
	
	private void pideString() {
	}
}
