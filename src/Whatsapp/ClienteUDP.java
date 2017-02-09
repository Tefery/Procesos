package Whatsapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {
	public static void main(String[] args) {
		final String SERVIDOR = "localhost";
		final int PUERTO = 51000;
		try {
			
			InetAddress infoServidor = InetAddress.getByName(SERVIDOR);
			DatagramSocket conexServ = new DatagramSocket(PUERTO);
			
			Scanner in = new Scanner(System.in);
			System.out.print("Mensaje: ");
			String mensaje = in.nextLine();
			System.out.println();

			DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, infoServidor,50000);
			conexServ.send(paquete);
			
			conexServ.close();
		} catch (UnknownHostException e) {
			System.err.println("No hay ningun ordenador en la red con ese nombre");
			e.printStackTrace();
		} catch (SocketException e) {
			System.err.println("El puerto ya está siendo utilizado");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Se ha producido un error al envia el paquete");			
			e.printStackTrace();
		}
	}
}
