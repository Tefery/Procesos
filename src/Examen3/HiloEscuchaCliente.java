package Examen3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class HiloEscuchaCliente extends Thread {
	
	Socket conexion;
	
	public HiloEscuchaCliente(Socket conexion) {
		this.conexion = conexion;
		this.start();
	}
	
	@Override
	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String mensaje = entrada.readLine();
			while(mensaje != null) {
				System.out.println(mensaje);
				System.out.println("Inserte un mensaje: ");
				mensaje = entrada.readLine();
			}
			entrada.close();
		} catch (SocketException e) {
                        System.out.println("El servidor ha cerrado. Se cierra la comunicación");
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado en la clase HiloEscuchaCliente");
			e.printStackTrace();
		}
	}
	
}
