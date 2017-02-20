package Examen3;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) {
		final String NOM_SERVIDOR = "localhost";
		final int PUERTO_ESC_SERVID = 2665;

		try {
			System.out.println("Voy a intentar ponerme a la escucha con el servidor ");
			Socket conexServ = new Socket(NOM_SERVIDOR, PUERTO_ESC_SERVID);
			new HiloEscuchaCliente(conexServ);
			new HiloEnviaCliente(conexServ);
		} catch (UnknownHostException e) {
			System.err.println("No existe, en la red, ningún ordenador con el nombre " + NOM_SERVIDOR);
		} catch (ConnectException e) {
			System.err.println("En el ordenador "+NOM_SERVIDOR+" no está escuchando por el puerto "+PUERTO_ESC_SERVID);
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado en la clase Cliente");
			e.printStackTrace();
		}
	}

}
