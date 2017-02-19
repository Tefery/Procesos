package Practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class HiloServidorEscucha extends Thread {

	String mensaje;
	Socket conexion;
	BufferedReader entrada;
	ArrayList<Socket> conexiones;
	PrintStream salida;

	public HiloServidorEscucha(Socket conexion, ArrayList<Socket> conexiones) {

		this.conexion = conexion;
		this.conexiones = conexiones;
		mensaje = "";
		setDaemon(true);
		start();

	}

	@Override
	public void run() {
		try {
			entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			while (mensaje != null) {
				mensaje = entrada.readLine();
				if (mensaje == null)
					break;
				for (Socket s : conexiones) {
					if (s != this.conexion) {
						salida = new PrintStream(s.getOutputStream());
						salida.println(mensaje);
					}
				}
			}
			conexiones.remove(conexion);
		} catch (SocketException e) {
			System.err.println("Un cliente ha cerrado la conexion de manera abrupta");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
