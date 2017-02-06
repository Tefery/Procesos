
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalS1 {

    public static void main(String[] args) {

        final int puertoEscuchaServidor = 2600;
        int puertoServer;
        Scanner pedir = new Scanner(System.in);

        try {

            ServerSocket con = new ServerSocket(puertoEscuchaServidor);
            puertoServer = con.getLocalPort();

            System.out.println("\n\t------El servidor se ha puesto a la escucha por el puerto: " + puertoEscuchaServidor + " ------");
            System.out.println("\t\t---Puerto asignado al cliente: " + puertoServer + " ---");
            System.out.println("\n\t------Esperando conexión del cliente------");
            Socket socket = con.accept();
            socket.getPort();

            System.out.println("\n\t------Alguien se ha conectado------");

            OutputStream salida = socket.getOutputStream();
            PrintStream sal = new PrintStream(salida, true);

            InputStream entrada = socket.getInputStream();
            BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));
            
            
            String nombre = "Paco";
            sal.println(nombre);

            String nombreCliente = ent.readLine();

            System.out.print("\n\t" + nombre + ": ");
            String men = pedir.nextLine();
            sal.println(men);

            if (men.equals("cerrar")) {
                System.out.println("\n\tHas cerrado la sesión");
            } else {
                while (!men.equals("cerrar")) {

                    men = ent.readLine();
                    if (men.equals("cerrar")) {
                        System.out.println("\n\t" + nombreCliente + " ha cerrado la sesión");
                        break;
                    } else {
                        System.out.println("\t" + nombreCliente + ": " + men);
                        System.out.print("\t" + nombre + ": ");
                        sal.println(men);
                        if (men.equals("cerrar")) {
                            System.out.println("\n\tHas cerrado la sesión");
                        } else {
                            men = pedir.nextLine();
                        }
                    }
                }
            }
            Thread.sleep(1500);

        } catch (IOException ex) {
            System.err.println("\n\t------No he podido ponerme a la escucha------\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(PrincipalS1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

/**
 * 0 - 1024 --> Puertos fijados por el sistema operativo :3 No es aconsejable
 * usarlos 1024 - 49151 --> Es el rango de puertes para aplicaciones
 * determiandas 49152 - 65535 --> Puertos asignados para aplicaciones del
 * sistema operativo
 *
 * 80 -> http4 25 -> Correo electrónico 21 -> FTP
 *
 *
 */
