package temp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {
	
	static String broadcastMask;
	
	public static void main(String[] args) throws UnknownHostException {
		final String SERVIDOR = GenerateBroadcastMask(InetAddress.getLocalHost().getAddress().toString().substring(1));
		final int PUERTO = 51000;
		byte[] addr = new byte[] { (byte) 192, (byte) 168, (byte) 1, (byte) 255 };
		try {
			InetAddress infoServidor = InetAddress.getByAddress(addr);
			DatagramSocket conexServ = new DatagramSocket(PUERTO);
			String mensaje = "asdaw";
			Scanner in = new Scanner(System.in);
			// while (!mensaje.equals("*")) {
			System.out.print("Mensaje: ");
			mensaje = "n:ojeteeeeeeeee";// InetAddress.getLocalHost().getAddress();
			while (mensaje.getBytes().length < 64) {
				mensaje += " ";
			}
			mensaje = mensaje.substring(0, 64);
			System.out.println(mensaje.getBytes().length);
			System.out.println(mensaje);
			DatagramPacket paquete = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, infoServidor,
					50000);
			conexServ.send(paquete);
			// }
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

	public static String GenerateBroadcastMask(String addr) {
		
		// revisa si la broadcast mask ya esta definida

		if (broadcastMask != null)
			return broadcastMask;

		// define la direccion ip en una cadena

		String localIP = addr;

		// encuentra el primer punto en la dir TCP/IP

		int dotPos = localIP.indexOf('.');
		if (dotPos != -1) {

			// Extrae el valor lider de la ip

			String ipStr = localIP.substring(0, dotPos);
			int ipVal = Integer.valueOf(ipStr).intValue();

			// Determina la broadcast mask a usar

			if (ipVal <= 127) {

				// direeccion clase A


				broadcastMask = "" + ipVal + ".255.255.255";
			} else if (ipVal <= 191) {

				// Direccion clase B

				dotPos++;
				while (localIP.charAt(dotPos) != '.' && dotPos < localIP.length())
					dotPos++;

				if (dotPos < localIP.length())
					broadcastMask = localIP.substring(0, dotPos) + ".255.255";
			} else if (ipVal <= 223) {

				// Direccion clase C

				dotPos++;
				int dotCnt = 1;

				while (dotCnt < 3 && dotPos < localIP.length()) {

					// Chequea si el caracter actual es un punto

					if (localIP.charAt(dotPos++) == '.')
						dotCnt++;
				}

				if (dotPos < localIP.length())
					broadcastMask = localIP.substring(0, dotPos - 1) + ".255";
			}
		}

		// chequea si la Broadcast mask esta definida sino define una
		// broadcast mask

		if (broadcastMask == null) {

			// Formato incorrecto de direccion TCP/IP, usa una broadcast mask
			// general
			// por ahora.

			broadcastMask = "255.255.255.255";
		}

		// retorna la broadcast mask en cadena

		return broadcastMask;
	}
}
