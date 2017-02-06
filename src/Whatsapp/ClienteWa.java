package Whatsapp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteWa {

    public static void main(String[] args) {

        final int puerto = 50000;
        final String nombreServior = "ELENA";

        try {

            System.out.println("\n------Buscando servidores en el puerto " + puerto + "------");
            Socket conexServer = new Socket(nombreServior, puerto);
            System.out.println("\n------Se ha establecido la conexión------\n");
        
            new ClienteHiloEnviar(conexServer);
            new ClienteHiloRecibir(conexServer);
            
        } catch (ConnectException e){
            System.err.println("\n\tEl puerto al que quiere acceder está ocupado o no hay ningún programa escuchando");
        } catch (UnknownHostException e){    
            System.err.println("\n\tNo existe la red con este nombre");
        } catch (IOException ex) {
            System.err.println("\n\tSe ha producido un error al enviar o recibir mensajes");
        }
    }

}
