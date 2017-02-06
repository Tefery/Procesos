
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalC1 {

    public static void main(String[] args) {

        String conxDavid = "10.228.89.158";
        String conxBrian = "10.228.89.159";
        final int puertoCliente = 5000;
        Scanner pedir = new Scanner(System.in);
        /*try { // Par averiguar el nombre de mi equipo
            InetAddress nom = InetAddress.getLocalHost();
            System.out.println(nom.getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(PrincipalC1.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        try {
            Socket con = new Socket("CODD06", puertoCliente);
            System.out.println("\t------Estamos a la escucha en el puerto: " + con.getPort() + " ------");
            System.out.println("\n\t-------Conexión realizada------");
            String men;

            System.out.println("\n" + con.getLocalSocketAddress());
            System.out.println(con.getInetAddress());
            System.out.println(con.getLocalAddress() + "\n");

            System.out.println("\n\t------Esperando mensaje del servidor------");

            InputStream entrada = con.getInputStream();
            BufferedReader ent = new BufferedReader(new InputStreamReader(entrada));

            OutputStream salida = con.getOutputStream();
            PrintStream sal = new PrintStream(salida, true);

            men = ent.readLine();

            while (!men.equals("cerrar")) {
                System.out.print("\tEl mensaje recibido es: " + men);

                System.out.print("\n\tMensaje a enviar: ");
                men = pedir.nextLine();
                if (men.equals("cerrar")) {
                    sal.println(men);
                } else {
                    sal.println(men);
                    men = ent.readLine();
                }
            }
            Thread.sleep(1500);

        } catch (IOException ex) {
            System.err.println("\n\t------No se ha establecido la conexión------\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(PrincipalC1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
