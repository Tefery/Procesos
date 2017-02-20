package Examen3;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Servidor {
	
	public static void main(String[] args) {
		
		final int PUERTO = 2665;
		Socket conexion;
		HashMap<String,Socket> conexiones = new HashMap<String,Socket>();
		
		System.out.println("Vamos a intentar ponernos a la escucha en el puerto "+PUERTO);
		try {
			ServerSocket esc = new ServerSocket(PUERTO);
			System.out.println("Nos hemos conseguido conectar");
			while(true) {
				conexion = esc.accept();
				System.out.println("Se ha conectado una nueva persona");
				new HiloEscuchaServidor(conexion,conexiones);
			}
		} catch (BindException e) {
			System.err.println("Lo siento, el puerto "+PUERTO+" está ocupado. Use otro puerto");
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado en la clase Servidor");
			e.printStackTrace();
		} 
	}
}
