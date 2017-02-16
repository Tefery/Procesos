package Practica3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {
	
	public static void main(String[] args) {
		
		final int PUERTO = 2600;
		int CONEX_MAX = 5;
		Socket conexion;
		ArrayList<Socket> conexiones = new ArrayList<>();
		ArrayList<PrintStream> salidas = new ArrayList<>();
		
		Scanner in = new Scanner(System.in);
		System.out.println("Vamos a intentar ponernos a la escucha en el puerto "+PUERTO);
		try {
			ServerSocket esc = new ServerSocket(PUERTO);
			System.out.println("Nos hemos conseguido conectar");
			System.out.print("¿Cuantas conexiones vamos a aceptar? ");
			CONEX_MAX = Integer.parseInt(in.nextLine());
			for(int i = 0 ; i < CONEX_MAX ; i++) {
				conexion = esc.accept();
				System.out.println("establecida una conexion");
				conexiones.add(conexion);
				salidas.add(new PrintStream(conexion.getOutputStream(), true));
				new HiloEscuchaServidor(conexion,conexiones, salidas);
			}
			System.out.println("no se aceptan mas conexiones");
			esc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
}
