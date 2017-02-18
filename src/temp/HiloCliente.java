package temp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class HiloCliente{
	
	private Socket conexion;
	private String nombre;
	private PrintStream salida;
	
	public HiloCliente(String nombre, Socket conexion) throws IOException {
		this.nombre = nombre;
		this.conexion = conexion;
		salida = new PrintStream(this.conexion.getOutputStream());
		
	}
	
	public void enviaMensaje(String mensaje) {
		
		salida.println(nombre+": "+mensaje);
		
	}
	
	public void close() {
		try {
			conexion.close();
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error al cerrar la conexión");
			e.printStackTrace();
		}
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
