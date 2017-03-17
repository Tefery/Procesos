package ClienteFTP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FTPui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2424121365284305335L;
	private final String HOST = "localhost";
	private final String USER = "alumno";
	private final String PASS = "alumno";

	private JList<String> listCarpetas;
	private JList<String> listArchivos;
	private JTextField txtRuta;

	private FTPClient cliente;
	private FTPFile[] listFiles;
	private PideString pideString;
	protected String retorno;
	protected boolean aceptaRetorno;
	private JLabel lblInformativo;

	public static void main(String[] args) {
		new FTPui();
	}

	public FTPui() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					cliente.disconnect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		pideString = new PideString(this);
		setSize(new Dimension(770, 478));
		setPreferredSize(new Dimension(770, 478));
		setMinimumSize(new Dimension(770, 478));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelPsicion = new JPanel();
		getContentPane().add(panelPsicion, BorderLayout.WEST);
		panelPsicion.setLayout(new BorderLayout(0, 0));

		JPanel panelPos = new JPanel();
		panelPsicion.add(panelPos, BorderLayout.NORTH);
		panelPos.setLayout(new BorderLayout(0, 0));

		JPanel panelruta = new JPanel();
		panelPos.add(panelruta, BorderLayout.NORTH);
		panelruta.setLayout(new BorderLayout(0, 0));

		txtRuta = new JTextField();
		txtRuta.setEditable(false);
		panelruta.add(txtRuta);
		txtRuta.setColumns(30);

		JLabel lblSeEncuentraEn = new JLabel("Se encuentra en el directorio:");
		panelruta.add(lblSeEncuentraEn, BorderLayout.NORTH);

		JPanel panelRenombrar = new JPanel();
		panelPos.add(panelRenombrar, BorderLayout.CENTER);
		panelRenombrar.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = listCarpetas.getSelectedValue();
				if (ad != null) {
					pideString.cambiaNombreOriginal(ad);
					pideString.setVisible(true);
					if (aceptaRetorno) {
						try {
							renombra(ad, retorno);
							aceptaRetorno = false;
							rellenaVentanas();
							informa(ad + " renombrado a " + retorno);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else
					mensajeInformativo("Seleccione algún elemento de la lista");
			}
		});

		panelRenombrar.add(btnRenombrar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = listCarpetas.getSelectedValue();
				if (ad != null) {
					try {
						if (pregunta("Atención", "¿Está seguro de querer borrar la carpeta " + ad + "?") == 0) {
							if (!borraCarpeta(ad))
								mensajeInformativo("El directorio no está vacio, vacie primero su contenido");
						}
						rellenaVentanas();
						informa(ad + " eliminado");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else
					mensajeInformativo("Seleccione algún elemento de la lista");
			}
		});
		panelRenombrar.add(btnEliminar);

		JPanel panelDirectorios = new JPanel();
		panelPsicion.add(panelDirectorios, BorderLayout.CENTER);
		panelDirectorios.setLayout(new BorderLayout(0, 0));

		JButton btnCrearCarpeta = new JButton("Crear Carpeta");
		btnCrearCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pideString.cambiaNombreOriginal("Nueva carpeta");
				pideString.setVisible(true);
				if (aceptaRetorno) {
					try {
						creaCarpeta(retorno);
						aceptaRetorno = false;
						rellenaVentanas();
						informa(retorno + " creado");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panelDirectorios.add(btnCrearCarpeta, BorderLayout.SOUTH);

		JLabel lblSubdirectorios = new JLabel("Subdirectorios (doble click para cambiar de directorio)");
		panelDirectorios.add(lblSubdirectorios, BorderLayout.NORTH);

		listCarpetas = new JList<String>();
		listCarpetas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						cambiaDirectorio(listCarpetas.getSelectedValue());
						String[] ad = cliente.printWorkingDirectory().split("/");
						if (ad.length != 0)
							informa("explorando " + ad[ad.length - 1]);
						else
							informa("explorando la raiz");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JScrollPane srollCarpetas = new JScrollPane(listCarpetas);
		panelDirectorios.add(srollCarpetas, BorderLayout.CENTER);

		JPanel panelFicheros = new JPanel();
		getContentPane().add(panelFicheros, BorderLayout.CENTER);
		panelFicheros.setLayout(new BorderLayout(0, 0));

		JPanel panelLitaFicheros = new JPanel();
		panelFicheros.add(panelLitaFicheros, BorderLayout.CENTER);
		panelLitaFicheros.setLayout(new BorderLayout(0, 0));

		listArchivos = new JList<String>();
		listArchivos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				List<String> ad = listArchivos.getSelectedValuesList();
				if (ad.size() == 1)
					informa(1 + " archivo seleccionado");
				else
					informa(listArchivos.getSelectedValuesList().size() + " archivos seleccionados");
			}
		});

		JScrollPane scrollArchivos = new JScrollPane(listArchivos);
		panelLitaFicheros.add(scrollArchivos, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Ficheros en el directorio actual:");
		panelLitaFicheros.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panelDescargar = new JPanel();
		panelLitaFicheros.add(panelDescargar, BorderLayout.SOUTH);
		panelDescargar.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnDescargar = new JButton("Descargar");
		btnDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ad = listArchivos.getSelectedValuesList();
				if (ad.size() != 0) {
					File ub = eligeRuta(JFileChooser.DIRECTORIES_ONLY);
					if (ub != null) {
						try {
							cliente.setFileType(FTP.BINARY_FILE_TYPE);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						cliente.enterLocalPassiveMode();
						String ab = ub.getAbsolutePath() + "\\";
						for (String s : ad)
							descargaArchivo(s, ab);
						if (ad.size() == 1)
							informa(ad.get(0) + " descargado en " + ub.getName());
						else
							informa(ad.size() + " archivos descargados en " + ub.getName());
					}
				} else
					mensajeInformativo("Seleccione algún elemento de la lista");
			}
		});
		panelDescargar.add(btnDescargar);

		JButton btnRenombrar2 = new JButton("Renombrar");
		btnRenombrar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad = listArchivos.getSelectedValue();
				if (ad != null) {
					pideString.cambiaNombreOriginal(ad);
					pideString.setVisible(true);
					if (aceptaRetorno) {
						try {
							renombra(ad, retorno);
							aceptaRetorno = false;
							rellenaVentanas();
							informa(ad + " renombrado a " + retorno);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else
					mensajeInformativo("Seleccione algún elemento de la lista");
			}
		});
		panelDescargar.add(btnRenombrar2);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> li = listArchivos.getSelectedValuesList();
				if (li.size() > 0) {
					String men;
					if (li.size() == 1) {
						men = "¿Está seguro de querer borrar " + li.get(0) + "?";
					} else {
						men = "¿Está seguro de querer borrar estos " + li.size() + " elementos?";
					}
					if (pregunta("Atención", men) == 0)
						if (!borraArchivo(li))
							mensajeInformativo("No se ha podido borrar el archivo");
						else
							try {
								rellenaVentanas();
								if (li.size() != 1)
									informa(li.size() + " archivos eliminados");
								else
									informa(li.get(0) + " eliminado");
							} catch (IOException e1) {
								e1.printStackTrace();
							}
				} else
					mensajeInformativo("Seleccione algún elemento de la lista");
			}
		});
		panelDescargar.add(btnBorrar);

		JButton btnSubir = new JButton("Subir");
		btnSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File ub = eligeRuta(JFileChooser.FILES_ONLY);
				try {
					subeArchivo(ub.getAbsolutePath(), ub.getName(), 0);
					rellenaVentanas();
					informa(ub.getName() + " subido en " + cliente.printWorkingDirectory());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelDescargar.add(btnSubir);

		JPanel panel = new JPanel();
		panelFicheros.add(panel, BorderLayout.WEST);

		lblInformativo = new JLabel("---     conectando     ---");
		lblInformativo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblInformativo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblInformativo, BorderLayout.NORTH);

		this.setVisible(true);

		conecta();
	}

	protected void mensajeInformativo(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	protected int pregunta(String titulo, String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje, titulo, 0, 3);
	}

	protected File eligeRuta(int tipo) {
		JFileChooser al = new JFileChooser();
		al.setFileSelectionMode(tipo);
		al.showOpenDialog(this);
		return al.getSelectedFile();
	}

	private void informa(String mensaje) {
		lblInformativo.setText("---      " + mensaje + "      ---");
	}

	private void conecta() {
		cliente = new FTPClient();
		try {
			cliente.connect(HOST);
			cliente.login(USER, PASS);
			cliente.enterLocalPassiveMode();
			rellenaVentanas();
			informa("conectado a " + HOST);
		} catch (IOException e) {
			mensajeInformativo("No es posible conectar con " + HOST);
			System.exit(0);
		}
	}

	private void cambiaDirectorio(String rutaDirectorio) throws IOException {
		cliente.changeWorkingDirectory(rutaDirectorio);
		rellenaVentanas();
	}

	private void rellenaVentanas() throws IOException {
		listFiles = cliente.listFiles();
		DefaultListModel<String> ld = new DefaultListModel<String>();
		ld.addElement("..");
		DefaultListModel<String> la = new DefaultListModel<String>();
		for (FTPFile f : listFiles) {
			if (f.isDirectory())
				ld.addElement(f.getName());
			else if (f.isFile()) {
				la.addElement(f.getName());
			}
		}
		listArchivos.setModel(la);
		listCarpetas.setModel(ld);
		txtRuta.setText(cliente.printWorkingDirectory());
	}

	private void creaCarpeta(String nombre) throws IOException {
		cliente.makeDirectory(nombre);
	}

	private void descargaArchivo(String rutaDescarga, String rutaArchivo) {
		BufferedOutputStream sal = null;

		try {
			sal = new BufferedOutputStream(new FileOutputStream(rutaArchivo + rutaDescarga));
			if (cliente.retrieveFile(rutaDescarga, sal)) {

			} else
				mensajeInformativo("No se ha podido descargar el archivo");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sal.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void subeArchivo(String ubicacion, String nombre, int tipo) throws FileNotFoundException, IOException {
		// tipo: 1 directorio y 0 fichero
		BufferedInputStream archivo = new BufferedInputStream(new FileInputStream(ubicacion));
		cliente.setFileType(tipo);
		cliente.storeFile(nombre, archivo);
	}

	private boolean borraArchivo(List<String> ruta) {
		try {
			for (String s : ruta) {
				if (!cliente.deleteFile(s))
					return false;
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean renombra(String origen, String Destino) throws IOException {
		return cliente.rename(origen, Destino);
	}

	private boolean borraCarpeta(String ruta) {
		try {
			return cliente.removeDirectory(ruta);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
