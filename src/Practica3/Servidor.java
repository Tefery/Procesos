package Practica3;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread {

	int puerto;
	boolean abierto;
	ServerSocket serv;
	Socket conexion;
	String mensaje;
	ArrayList<Socket> conexiones;

	public Servidor(int puerto) throws IOException {
		this.puerto = puerto;
		serv = new ServerSocket(puerto);
		mensaje = "";
		abierto = true;
		conexiones = new ArrayList<>();
	}

	public static void main(String[] args) {
		final int puerto = 39876;
		Servidor serv;
		try {
			serv = new Servidor(puerto);
			serv.start();
		} catch (BindException e) {
			System.err.println("El puerto " + puerto + " ya está en uso por otra aplicación");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			while (abierto) {
				conexion = serv.accept();
				conexiones.add(conexion);
				new HiloServidorEscucha(conexion, conexiones);
			}
			serv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
