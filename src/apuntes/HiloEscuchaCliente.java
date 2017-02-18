package apuntes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
			mensaje = entrada.readLine();
			while(mensaje != null) {
				System.out.println(mensaje);
				mensaje = entrada.readLine();
			}
			conexion.close();
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}
	}
	
}
