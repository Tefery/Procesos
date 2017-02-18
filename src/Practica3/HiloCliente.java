package Practica3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class HiloCliente{
	
	private Socket conexion;
	private String nombre;
	private PrintStream salida;
	
	public HiloCliente(String nombre, Socket conexion) {
		this.nombre = nombre;
		this.conexion = conexion;

		try {
			salida = new PrintStream(this.conexion.getOutputStream());
		} catch (IOException e) {
			System.err.println("El Socket no tiene un formato correcto");
			e.printStackTrace();
		}
	}
	
	public void enviaMensaje(String mensaje) {
		
		salida.println(nombre);
		salida.println(mensaje);
		
	}
	
	public void close() {
		try {
			conexion.close();
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error al cerrar la conexión");
			e.printStackTrace();
		}
	}
}
