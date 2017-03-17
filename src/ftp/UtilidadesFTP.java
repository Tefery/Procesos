

package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;



public class UtilidadesFTP {
    
    public static void cambiarCarpeta(FTPClient conex,String camino)
    {
        try {
            if (conex.changeWorkingDirectory(camino)) {
                System.out.println("Hemos cambiado de carpeta");
            }
            else
                System.out.println("No se ha podido cambiar a la carpeta "+camino);
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void subirFichero(FTPClient conex, String pathNameFicSubir)
   {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\excursiones\\solicitud.doc"));
            conex.setFileType(FTP.BINARY_FILE_TYPE);
            
            conex.enterLocalPassiveMode();
            
            if(conex.storeFile(pathNameFicSubir, in))
                System.out.println("subido");
            else
                System.out.println("Ha habido algún problema.");
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        }

       
   }
    
    
    
    public static void cambiarNombre(FTPClient conex, String nomDirectorioCrear)
   {
        try {
            if (conex.makeDirectory(nomDirectorioCrear)) {
                System.out.println("Se ha creado la carpeta");
            } else {
                System.out.println("No se ha creado.");
            }
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public static void cambiarNombre(FTPClient conex, String nomActual, String nuevoNombre)
   {
        try { 
            conex.rename(nomActual, nuevoNombre);
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
       
   
     
      public static void listar(FTPClient conex)
    {
        try {
            conex.enterLocalPassiveMode();
            FTPFile[] listFiles = conex.listFiles();
            System.out.println("Hay " + listFiles.length + " ficheros o directorios.");
            for (int pos = 0; pos < listFiles.length; pos++) {
                System.out.println("nombre:" + listFiles[pos].getName());
                
                System.out.println("Tamaño: " + listFiles[pos].getType());
            }
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void borrarCarpeta(FTPClient conex,String camino)
    {
      
        try {
            conex.removeDirectory(camino);
        } catch (IOException ex) {
            System.out.println("Ha habido algún error.");
        }
        
    }
    public static void descargarFichero(FTPClient conex)
            
    {
        BufferedOutputStream sal=null;
        try {
            sal = new BufferedOutputStream(new FileOutputStream("d:\\excursiones\\bajado.pdf"));
            conex.setFileType(FTP.BINARY_FILE_TYPE);
            conex.enterLocalPassiveMode();
           if( conex.retrieveFile("comprobante.pf", sal))
           {
               System.out.println("Bajado con éxito");
           }
           else
                System.out.println("No se ha podido bajar.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sal.close();
            } catch (IOException ex) {
                Logger.getLogger(UtilidadesFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
   
    {
        
    }

}
