package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ClienteDNS {
	public static void main(String[] args) {
		String mensaje;
		Scanner in = new Scanner(System.in);
		DatagramSocket escucha;
		DatagramPacket paquete;
		try {
			escucha = new DatagramSocket(50000);
			System.out.print("Mensaje: ");
			mensaje = in.nextLine();
			paquete = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, InetAddress.getByName("elena"), 60000);
			escucha.send(paquete);
			System.out.println("mensaje enviado");
			paquete = new DatagramPacket(new byte[1024], 1024);
			escucha.receive(paquete);
			mensaje = new String(paquete.getData());
			System.out.println("La respuesta del servidor es: " + mensaje);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		in.close();
	}
}
