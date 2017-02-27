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
	ServidorUI ventana;
	BufferedReader entrada;
	ArrayList<Socket> conexiones;
	PrintStream salida;

	public HiloServidorEscucha(Socket conexion, ArrayList<Socket> conexiones, ServidorUI ventana) {
		this.ventana = ventana;
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
				ventana.addText(mensaje);
				for (Socket s : conexiones) {
					if (s != this.conexion) {
						salida = new PrintStream(s.getOutputStream());
						salida.println(mensaje);
					}
				}
			}
			ventana.addText("Se han desconectado desde "+conexion.getInetAddress());
			conexiones.remove(conexion);
		} catch (SocketException e) {
			ventana.addText("El cliente desde "+conexion.getInetAddress()+" ha cerrado de manera abrupta");
			conexiones.remove(conexion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
