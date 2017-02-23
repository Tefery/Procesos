package Abismo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHiloEnviar extends Thread{
    
    Socket conex;
    Scanner pedir = new Scanner(System.in);
    String nombreCliente, men;
    
    ClienteHiloEnviar(Socket conex){
        this.conex = conex;
        start();
    } 
    
    @Override
    public void run(){
        OutputStream salida = null;
        try {
            
            salida = conex.getOutputStream();                     
            PrintStream sal = new PrintStream(salida, true);      
            
            System.out.print("\n\tIntroduce tu nombre: ");
            nombreCliente = pedir.nextLine();
            sal.println(nombreCliente);
            
            System.out.print("\n\tMensaje: ");
            men = pedir.nextLine();
            
            while (!men.equals("adios")) {
                
                sal.println(men);

                if (sal.checkError()) {
                    System.err.println("El servidor se ha ido");
                    break;
                } else {
                    System.out.print("\tMensaje: ");
                    men = pedir.nextLine();
                }
            }
            
            conex.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHiloEnviar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorHiloEnviar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
