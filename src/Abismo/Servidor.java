package Abismo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Servidor {

	static int SOCKET;

	public static void main(String[] args) {

		SOCKET = 5000;

		ServerSocket conex = null;

		Scanner in = null;
		try {

			try {
				System.out.println(InetAddress.getLocalHost().getHostName());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			in = new Scanner(System.in);

			conex = ponerEscucha();

			System.out.println("se ha puesto a la escucha en el puerto " + SOCKET);

			Socket conexCliente = null;

			conexCliente = conex.accept();

			System.out.println("Se ha conectado aguien desde la direccion " + conexCliente.getLocalAddress()
					+ " en el puerto " + conexCliente.getPort());

			PrintStream salida = null;

			salida = new PrintStream(conexCliente.getOutputStream(), true);

			BufferedReader entrada = null;

			entrada = new BufferedReader(new InputStreamReader(conexCliente.getInputStream()));

			System.out.println("Introduzca el mensaje: ");

			String mensajeSalida = "";
			String mensajeEntrada = "";

			while (!mensajeSalida.equals("cerrar") && !mensajeEntrada.equals("cerrar")) {
				mensajeSalida = in.nextLine();
				salida.println(mensajeSalida);
				if (mensajeSalida.equals("cerrar"))
					break;
				mensajeEntrada = entrada.readLine();
				System.out.println(mensajeEntrada);
			}
			
			espera(1000);
			
			System.out.println("Se ha cerrado la conexion");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			in.close();
			try {
				conex.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void espera(int i) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static ServerSocket ponerEscucha() {
		ServerSocket conex = null;
		try {
			conex = new ServerSocket(SOCKET);
		} catch (BindException e) {
			SOCKET++;
			return ponerEscucha();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conex;
	}
}
