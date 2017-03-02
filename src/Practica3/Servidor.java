package Practica3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread {

	int puerto;
	boolean abierto;
	ServerSocket serv;
	Socket conexion;
	String mensaje;
	ServidorUI ventana;
	ArrayList<Socket> conexiones;

	public Servidor(int puerto, ServidorUI ventana) throws IOException {
		this.puerto = puerto;
		this.ventana = ventana;
		serv = new ServerSocket(puerto);
		this.ventana.addText("Escuchando por el puerto " + puerto);
		mensaje = "";
		abierto = true;
		conexiones = new ArrayList<>();
	}

	@Override
	public void run() {
		try {
			for (int i = 0; abierto; i++) {
				conexion = serv.accept();
				conexiones.add(conexion);
				ventana.addText("Se han conectado desde " + conexion.getInetAddress());
				new HiloServidorEscucha(conexion, conexiones, ventana, "conexion" + i + ":");
			}
			serv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
