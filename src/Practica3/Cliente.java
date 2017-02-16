package Practica3;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		final int PUERTO = 2600;
		String NOMBRE_SERVIDOR = "localhost";
		
		System.out.println("Vamos a intentar conectarnos con el servidor");
		
		try {
			Scanner in = new Scanner(System.in);
			Socket conex = new Socket(NOMBRE_SERVIDOR, PUERTO);
			System.out.println("conseguido");
			System.out.println("Introduce el nombre de usuario");
			String nombre = in.nextLine();
			new HiloEscuchaCliente(conex);
			new HiloEnvia(conex, nombre);
			in.close();
		} catch (UnknownHostException e) {
			System.err.println("No se ha encontrado el host: "+NOMBRE_SERVIDOR);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}
	}
}
