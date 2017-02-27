package ftp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP {
	public static void main(String[] args) {

		final String host = "ftp.brianclase.esy.es";

		FTPClient cliente = new FTPClient();
		try {
			cliente.connect(host);
			System.out.println("Nos hemos conectado al puerto " + cliente.getRemotePort());
			String respuesta = cliente.getReplyString();
			int codigoRespuesta = cliente.getReplyCode();
			if (!FTPReply.isPositiveCompletion(codigoRespuesta)) {
				System.err.println(respuesta);
				System.exit(0);
			}
			cliente.login("u750565235", "alumno");
			System.out.println(respuesta);
			cliente.enterLocalPassiveMode();
			subeArchivo(cliente,"C:\\Users\\Tefery\\Desktop\\libreriasFTP\\jakarta-oro-2.0.1.jar","jakarta.jar");
			cliente.deleteFile("jakarta.jar");
			//cliente.changeWorkingDirectory("ojete");
			
			FTPFile[] listFiles = cliente.listFiles();
			
			//cliente.remoteAppend("C:\\Users\\Tefery\\Desktop\\jakarta-oro-2.0.1.jar");
			
			for(FTPFile f : listFiles) 
				System.out.println(f.getName());
			
			System.out.println("Estoy en: "+cliente.printWorkingDirectory());
			
			//cliente.rename("manuel", "/public_html/manolo");
			
			System.out.println("Estoy en: "+cliente.printWorkingDirectory());
		} catch (UnknownHostException e) {
			System.err.println("No se encuentra el servidor " + host);
		} catch (ConnectException e) {
			System.err.println("No hay acceso al puerto 21 desde" + host);
		} catch (SocketException e) {
			System.err.println("Se ha interrumpido la conexion con " + host);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void subeArchivo(FTPClient cliente, String ubicacion, String nombre) throws FileNotFoundException, IOException {
		BufferedInputStream archivo = new BufferedInputStream(new FileInputStream(ubicacion));
		
		cliente.storeFile(nombre, archivo);
	}
	
}
