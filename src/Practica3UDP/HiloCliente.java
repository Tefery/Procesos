package Practica3UDP;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class HiloCliente extends Thread {

	private DatagramSocket conexion;
	private DatagramPacket paquete;
	private ClienteUI clienteUI;
	private String direccion;

	public HiloCliente(DatagramSocket conexion, DatagramPacket paquete, ClienteUI clienteUI, String direccion) throws IOException {
		this.direccion = direccion;
		this.conexion = conexion;
		this.paquete = paquete;
		this.clienteUI = clienteUI;
		start();
	}

	@Override
	public void run() {
		try {
			String mensaje = "";
			while (clienteUI.abierto) {
				conexion.receive(paquete);
				mensaje = new String(paquete.getData());
				if (!paquete.getAddress().toString().substring(1).equals(direccion)) {
					if (!clienteUI.abierto)
						break;
					if (mensaje.startsWith("m:")) {
						mensaje = mensaje.substring(2).trim();
						clienteUI.addTexto(mensaje);
					} else if (mensaje.startsWith("n:")) {
						mensaje = mensaje.substring(2);
						clienteUI.cambiaNombre(mensaje.trim(), paquete.getAddress().toString());
					} else if (mensaje.startsWith("x:")) {
						mensaje = mensaje.substring(2);
						clienteUI.cambiaNombre(mensaje.trim(), null);
					} else if (mensaje.startsWith("f:")) {
						clienteUI.enviaNombre();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		conexion.close();
	}

	public void cerrar() {
		conexion.close();
	}
}
