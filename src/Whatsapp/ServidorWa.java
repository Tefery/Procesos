package Whatsapp;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorWa {

    public static void main(String[] args) {

        final int puertoServer = 5000;
        Socket conexCliente;
        Scanner pedir = new Scanner(System.in);
        String men;

        try {

            ServerSocket server = new ServerSocket(puertoServer);
            System.out.println("\n------Estamos a la espera de una conexión------");

            conexCliente = server.accept();

            System.out.println("\n------Un cliente se ha conectado------\n");

            ServidorHiloEnviar hiloEnviar = new ServidorHiloEnviar(conexCliente);
            ServidorHiloRecibir hiloRecibir = new ServidorHiloRecibir(conexCliente,hiloEnviar);
            
            
            
        } catch (BindException e){
            System.err.println("\n\tLo siento el puerto ya está a la escucha");
        } catch (IOException ex) {
            System.err.println("\n\tSe ha producido un error en la entrada y la salida de datos");
        }
    }

}
