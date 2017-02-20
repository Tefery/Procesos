package Examen3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class HiloEscuchaServidor extends Thread {

	Socket conexion;
	HashMap<String, Socket> conexiones;
	PrintStream miSalida;
	PrintStream suSalida;
	BufferedReader entrada;
	boolean hayRemitente;
	String nombre;
	String remitente;
	String mensaje;
	String[] mensaj;

	public HiloEscuchaServidor(Socket conexion, HashMap<String, Socket> conexiones) {
		this.conexion = conexion;
		this.conexiones = conexiones;
		this.hayRemitente = false;
		this.start();
	}

	@Override
	public void run() {
		try {
			this.entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			this.miSalida = new PrintStream(conexion.getOutputStream(), true);
			mensaje = entrada.readLine();
			nombre = mensaje;
			if (conexiones.containsKey(nombre)) {
				miSalida.println("Este nombre ya existe, elija otro nombre por favor");
				this.stop();
			}
			conexiones.put(nombre, conexion);
			System.out.println("Una de las personas, se ha identificado como " + nombre);
			mensaje = entrada.readLine();
			while (mensaje != null && mensaje.contains(":")) {
				hayRemitente = false;
				mensaj = mensaje.split(":");
				remitente = mensaj[0];
				mensaje = mensaj[1];
				for (String s : conexiones.keySet()) {
					if (s.equals(remitente)) {
						try {
							suSalida = new PrintStream(conexiones.get(remitente).getOutputStream(), true);
							suSalida.println(nombre + ": " + mensaje);
							miSalida.println("el mensaje " + mensaje + " ha sido enviado a " + remitente);
							hayRemitente = true;
						} catch (IOException e) {
							miSalida.println(
									"Lo siento, el mensaje " + mensaje + " no se ha podido enviar a " + remitente);
						}
					}
				}
				if (!hayRemitente)
					miSalida.println("No existe, el la red, ningún ordenador con el nombre " + remitente);
				mensaje = entrada.readLine();
			}
			System.out.println(nombre + " se ha ido.");
			conexiones.remove(nombre);
		} catch (SocketException e) {
			if (nombre != null) {
				System.err.println("Lo siento, " + nombre + " se ha ido. Ha cerrado MAL");
				conexiones.remove(nombre);
			} else
				System.err
						.println("Se ha conectado alguien, pero se ha ido antes de insertar su nombre. Ha cerrado MAL");
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado en la clase HiloEscuchaServidor");
			e.printStackTrace();
		}
	}
}
