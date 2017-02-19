package temp;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
	public static void main(String[] args) {
		String mensaje;
		try {
			DatagramSocket conexCliente = new DatagramSocket(50000);
			DatagramPacket paqueteRecibido;
			byte[] mensaj = new byte[64];
			paqueteRecibido = new DatagramPacket(mensaj, mensaj.length);
			System.out.println("Esperando el paquete");
			while (true) {
				conexCliente.receive(paqueteRecibido);
				mensaje = new String(paqueteRecibido.getData());
				if (!paqueteRecibido.getAddress().equals(conexCliente.getLocalAddress())) {
					if (mensaje.startsWith("m:")) {
						mensaje = mensaje.substring(2).trim();
						System.out.println(mensaje);
					} else if (mensaje.startsWith("n:")) {
						mensaje = mensaje.substring(2);
						System.out.println(mensaje.trim()+", "+paqueteRecibido.getAddress());
					} else if (mensaje.startsWith("x:")) {
						mensaje = mensaje.substring(2);
						System.out.println(mensaje.trim() + " ha cerrado");
					} else {
						System.out.println(mensaje);
					}
				} else {
					System.out.println("Soy yo" + "\n");
				}
			}
		} catch (BindException e) {
			System.err.println("El puerto indicado ya está siendo utilizado");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Ha habido un error al recibir el paquete");
			e.printStackTrace();
		}
	}
}
