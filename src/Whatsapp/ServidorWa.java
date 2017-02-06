package Whatsapp;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorWa {

    public static void main(String[] args) {

        final int puertoServer = 5000;
        Socket conexCliente;
        ServerSocket server;

        try {

			server = new ServerSocket(puertoServer);
            System.out.println("\n------Estamos a la espera de una conexión------");

            conexCliente = server.accept();

            System.out.println("\n------Un cliente se ha conectado------\n");

            new ServidorHiloEnviar(conexCliente);
            new ServidorHiloRecibir(conexCliente);
            
            
            
        } catch (BindException e){
            System.err.println("\n\tLo siento el puerto ya está a la escucha");
        } catch (IOException ex) {
            System.err.println("\n\tSe ha producido un error en la entrada y la salida de datos");
        }
    }

}
