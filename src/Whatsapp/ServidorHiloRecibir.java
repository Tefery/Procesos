package Whatsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHiloRecibir extends Thread {

    Socket conex;
    Scanner pedir = new Scanner(System.in);
    String nombreCliente, men;

    ServidorHiloRecibir(Socket conex) {
        this.conex = conex;
        this.setDaemon(true);
        start();
    }

    @Override
    public void run() {

        InputStream entrada = null;

        try {

            entrada = conex.getInputStream();
            BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));

            nombreCliente = ent.readLine();
            System.out.println("\n\tSe ha conectado -" + nombreCliente + "-\n");

            men = ent.readLine();

            while (men != null) {
                System.out.println("\t" + nombreCliente + " :" + men);
                men = ent.readLine();
            }

            System.err.println("\n\t------El cliente " + nombreCliente + " se ha salido correctamente------");

        } catch (SocketException e) { // Ocurre si la salida del cliente se ha caido
            System.err.println("\n\tEl cliente " + nombreCliente + " se ha caido");
        } catch (IOException ex) {
            System.err.println("\n\tSe ha producido un error en la entrada de mensaje");
        } finally {
            try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
