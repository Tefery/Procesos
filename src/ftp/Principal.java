package ftp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Principal {

    public static void main(String[] args) {
        final String NOMSERV = "ftp.elenaperez.esy.es";
        FTPClient cliente = new FTPClient();
        String usuario="",clave="";
        try {
            cliente.connect(NOMSERV);
            System.out.println("El servidor escucha por el puerto: " + cliente.getRemotePort());
            System.out.println("Se ha conectado");
            String menServ = cliente.getReplyString();
            System.out.println("El mensaje del servidor es " + menServ);
            int menServInt = cliente.getReplyCode();
            System.out.println("El código recibido es " + menServInt);
            if (!FTPReply.isPositiveCompletion(menServInt)) {
                System.exit(2);
            }
            cliente.login(usuario,clave );

            if (cliente.changeWorkingDirectory("antiguo")) {
                System.out.println("Hemos bajado.");
                System.out.println("Estás ahora en " + cliente.printWorkingDirectory());
                cliente.enterLocalPassiveMode();
                FTPFile[] listFiles = cliente.listFiles();
                System.out.println("Hay " + listFiles.length + " ficheros o directorios.");
                for (int pos = 0; pos < listFiles.length; pos++) {
                    System.out.println("nombre:" + listFiles[pos].getName());
                   
                    System.out.println("Tamaño: " + listFiles[pos].getType());
                }
                cliente.rename("nuevo.mp3", "hola/cambiado.mp3");
                if (cliente.makeDirectory("hola/patata")) {
                    System.out.println("Se ha creado la carpeta");
                } else {
                    System.out.println("No se ha creado.");
                }

            }

            BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\excursiones\\solicitud.doc"));
            cliente.setFileType(FTP.BINARY_FILE_TYPE);

            cliente.enterLocalPassiveMode();
            
            if(cliente.storeFile("subido.docx", in))
                System.out.println("subido");
            else
                System.out.println("Ha habido algún problema.");

            
            UtilidadesFTP.descargarFichero(cliente);
            
            UtilidadesFTP.borrarCarpeta(cliente, "/html_public/antiguo/hola");
        } catch (ConnectException e) {
            System.out.println("Lo siento, no escucha por ese puerto.");
        } catch (UnknownHostException e) {
            System.out.println("Lo siento, NO EXISTE NINGUN SERVIDOR FTP CON EL NOMBRE  " + NOMSERV);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
