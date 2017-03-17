package ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
			cliente.login("u750565235", "lumno");
			System.out.println(respuesta);
			cliente.enterLocalPassiveMode();
			subeArchivo(cliente,"C:\\Users\\Tefery\\Desktop\\libreriasFTP\\jakarta-oro-2.0.1.jar","jakarta.jar",0);
			//cliente.deleteFile("jakarta.jar");
			descargaArchivo(cliente, "/public_html/jakarta.jar","C:\\Users\\Tefery\\Desktop\\jaka.jar");
			//cliente.changeWorkingDirectory("ojete");
			
			FTPFile[] listFiles = cliente.listFiles();
			
			borraArchivo(cliente, "/public_html/jakarta.jar");
			borraCarpeta(cliente, "ojete", false);
			
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

	private static void descargaArchivo(FTPClient cliente, String rutaDescarga, String rutaArchivo) {
		try {
			cliente.retrieveFile(rutaDescarga, new BufferedOutputStream(new FileOutputStream(rutaArchivo)));
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void subeArchivo(FTPClient cliente, String ubicacion, String nombre, int tipo) throws FileNotFoundException, IOException {
		// tipo: 1 directorio y 0 fichero
		BufferedInputStream archivo = new BufferedInputStream(new FileInputStream(ubicacion));
		cliente.setFileType(tipo);
		cliente.storeFile(nombre, archivo);
	}
	
	private static boolean borraArchivo(FTPClient cliente, String ruta) {
		try {
			cliente.deleteFile(ruta);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean borraCarpeta(FTPClient cliente, String ruta, boolean arrasa) {
		try {
			cliente.removeDirectory(ruta);
			return true;
		} catch (IOException e) {
			if(arrasa) {
				try {
					for(FTPFile f : cliente.listFiles()) {
						if(f.isFile()) {
							boolean resul = borraArchivo(cliente,f.getLink());
							if(!resul)
								return false;
						} else {
							boolean resul = borraCarpeta(cliente,f.getLink(),arrasa);
							if(!resul)
								return false;
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					return false;
				}
				try {
					cliente.removeDirectory(ruta);
					return true;
				} catch (IOException e1) {
					e1.printStackTrace();
					return false;
				}
			}
			e.printStackTrace();
			return false;
		}
	}
}
