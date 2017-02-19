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
				if(mensaje == null)
					break;
				clienteUI.addTexto(mensaje);
			}
			conexion.close();
		} catch (SocketException e) {
			clienteUI.servidorCaido("El servidor ha cerrado de manera inesperada");
		} catch (IOException e) {
			clienteUI.servidorCaido("Ha ocurrido un error inesperado");
		}
	}
}
