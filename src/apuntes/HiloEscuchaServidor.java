package apuntes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class HiloEscuchaServidor extends Thread {
	
	Socket conexion;
	ArrayList<Socket> conexiones;
	ArrayList<PrintStream> salidas;
	
	public HiloEscuchaServidor(Socket conexion, ArrayList<Socket> conexiones, ArrayList<PrintStream> salidas) {
		this.conexion = conexion;
		this.conexiones = conexiones;
		this.salidas = salidas;
		this.start();
	}
	
	
	@Override
	public void run() {
		try {
			InputStream inputStream = conexion.getInputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(inputStream));
			String mensaje = entrada.readLine();
			while(mensaje != null) {
				for(int i = 0; i < conexiones.size() ; i++) {
					if(this.conexiones.get(i) != this.conexion)
					this.salidas.get(i).println(mensaje);;
				}
				mensaje = entrada.readLine();
			}
		} catch (SocketException e) {
			System.err.println("Se ha interrumpido la conexion de forma inesperada");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
