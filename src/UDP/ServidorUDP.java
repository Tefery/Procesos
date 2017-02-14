package UDP;

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
			byte[] mensaj = new byte[1024];
			paqueteRecibido = new DatagramPacket(mensaj, mensaj.length);
			System.out.println("Esperando el paquete");
			conexCliente.receive(paqueteRecibido);
			System.out.println("paquete recibido");
			mensaje = new String(paqueteRecibido.getData());
			System.out.println("Mensaje: "+ mensaje);
			System.out.println("Tamaño del mensaje es: "+paqueteRecibido.getLength());
			System.out.println("El remitente es: "+paqueteRecibido.getAddress().getHostName()+":"+paqueteRecibido.getPort());
			while(!mensaje.equals("*")){
				conexCliente.receive(paqueteRecibido);
				mensaje = new String(paqueteRecibido.getData());
				System.out.println(mensaje);
			}
			
			conexCliente.close();
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
