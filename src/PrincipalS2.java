
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalS2 {

    public static void main(String[] args) {

        final int puerto = 2600;
        Socket conexion;
        String mensaje;
        Scanner pedir = new Scanner(System.in);

        try {
            ServerSocket server = new ServerSocket(puerto);
            System.out.println("\n\t------El servidor se ha puesto a la escucha por el puerto " + puerto + "------");
            conexion = server.accept();
            server.close();
            
            OutputStream salida = conexion.getOutputStream(); // Salida de mensajes
            PrintStream sal = new PrintStream(salida, true);

            InputStream entrada = conexion.getInputStream(); // Entrada de mensajes
            BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));

            System.out.println("\n\tEstamos esperando un mensaje del cliente.");
            System.out.println(" --" + conexion.getInetAddress().getHostName() + " -puerto: " + conexion.getPort() + "\n"); // Nombre y el puerto de quien lo envia 

            System.out.println("\tMensaje (cliente): " + ent.readLine()); // Mensaje del cliente

            System.out.print("\tMensaje (servidor): ");
            mensaje = pedir.nextLine();
            sal.println(mensaje); // Enviar mensaje al cliente

            mensaje = ent.readLine();

            while (!mensaje.equals("adios")) {
                System.out.println("\tMensaje cliente: " + mensaje); // Mensaje del cliente

                System.out.print("\tMensaje (servidor): ");
                mensaje = pedir.nextLine();
                if (mensaje.equals("adios")) {
                    System.out.println("\n\tEl cliente ha cerrado la sesión");
                    break;
                }

                sal.println(mensaje); // Enviar mensaje al cliente

                mensaje = ent.readLine(); // Recibe mensaje del cliente
            }

            conexion.close(); // Cierra la conexión
            System.out.println("\n\t------La conexón ha sido cerrada-------");

        } catch (BindException e) { // Ocurre cuando el puerto ya está en uso
            System.err.println("\n\tEl puerto al que quiere acceder está ocupado");
        } catch (SocketException e) { // Ocurre si la salida del cliente se ha caido
            System.err.println("\n\tEl cliente se ha caido");
        } catch (IOException ex) {
            System.err.println("\n\tSe ha producido un error. " + ex.getMessage() + " | " + ex.toString());
            Logger.getLogger(PrincipalS2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
