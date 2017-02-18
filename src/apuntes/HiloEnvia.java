package apuntes;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class HiloEnvia extends Thread{
	
	Socket conexion;
	Scanner in;
	String nombre;
	
	public HiloEnvia(Socket conexion, String nombre) {
		this.conexion = conexion;
		this.nombre = nombre;
		in = new Scanner(System.in);
		this.setDaemon(true);
		this.start();
	}
	
	@Override
	public void run() {
		
		try {
			PrintStream salida = new PrintStream(conexion.getOutputStream());
			String mensaje = in.nextLine();
			while(!mensaje.equals("adios")) {
				salida.println(nombre+": "+mensaje);
				mensaje = in.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
