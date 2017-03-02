package Practica3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class HiloCliente extends Thread {

	private Socket conexion;
	private BufferedReader entrada;
	private ClienteUI clienteUI;

	public HiloCliente(Socket conexion, ClienteUI clienteUI) throws IOException {
		this.conexion = conexion;
		this.clienteUI = clienteUI;
		entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		start();
	}

	@Override
	public void run() {
		try {
			String mensaje = "";
			while (clienteUI.abierto && mensaje != null) {
				mensaje = entrada.readLine();
				if (mensaje == null)
					break;
				else if (mensaje.startsWith("m:"))
					clienteUI.addTexto(mensaje.substring(2));
				else if (mensaje.startsWith("f:")) {
					clienteUI.enviaNombre();
				} else if (mensaje.startsWith("n:")) {
					String[] mensaje2 = mensaje.split(":");
					clienteUI.guardaNombre(mensaje2[2], mensaje2[1]);
				}
			}
			conexion.close();
		} catch (SocketException e) {
			clienteUI.servidorCaido("El servidor ha cerrado de manera inesperada");
		} catch (IOException e) {
			clienteUI.servidorCaido("Ha ocurrido un error inesperado");
		}
	}

	public void cerrar() {
		try {
			entrada.close();
			conexion.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
