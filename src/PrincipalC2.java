
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalC2 {

    public static void main(String[] args) {
        String servidor = "ELENA"; // Nombre del servidor
        int puerto = 60000; // Puerto al que te conectas
        String mensaje;
        Scanner pedir = new Scanner(System.in);

        System.out.println("\n\t------Conectando con el servidor------");

        try {
            Socket conexion = new Socket(servidor, puerto);

            System.out.println("\n\t------Conexión establecioda------\n");

            InputStream entrada = conexion.getInputStream();
            BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));

            OutputStream salida = conexion.getOutputStream();
            PrintStream sal = new PrintStream(salida, true);

            System.out.print("\tMensaje (cliente): ");
            mensaje = pedir.nextLine();
            sal.println(mensaje);

            while (!mensaje.equals("adios")) {

                System.out.print("\tMensaje (servidor):");
                mensaje = ent.readLine();
                if (mensaje.equals("adios")) {
                    System.err.println("\n\t-------El servidor ha cerrado el chat------");
                    break;
                } else {
                    System.out.println(mensaje);
                }
                System.out.print("\tMensaje (cliente): ");
                mensaje = pedir.nextLine();
                sal.println(mensaje);
            }

        } catch (UnknownHostException e) { // Si el servidor no exite
            System.err.println("\n\tSe ha producido un error");
        } catch (ConnectException e) { // Existe el servidor pero no esta abierto el puerto
            System.err.println("\n\tEl puerto al que quiere acceder está ocupado");
        } catch (SocketException e) { // Cuando te dejan colgado
            System.err.println("\n\tEl servidor se ha caido");
        } catch (IOException ex) { // Este... bueno, en general <3
            System.err.println("\n\tSe ha producido un error. " + ex.getMessage() + " | " + ex.toString());
            Logger.getLogger(PrincipalC2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
