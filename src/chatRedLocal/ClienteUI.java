package chatRedLocal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ClienteUI extends JFrame {

	public ClienteUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				abierto = false;
				try {
					if (conexion != null)
						conexion.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		abierto = true;
		nombre = "Anonimo";

		setTitle("Chat 3000 - Desconectado");
		setSize(558, 515);

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		panelPrincipal.add(panelBotones, BorderLayout.NORTH);
		panelBotones.setLayout(new BorderLayout(0, 0));

		JLabel lblDireccionHost = new JLabel("Direccion del host:");
		panelBotones.add(lblDireccionHost, BorderLayout.WEST);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.setFocusable(false);
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		panelBotones.add(btnConectar, BorderLayout.EAST);

		textFieldHost = new JTextField();
		textFieldHost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldHost.getText().length() < 29 || e.getKeyCode() == 10) {
				} else {
					e.setKeyChar('\0');
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					conectar();
			}
		});
		panelBotones.add(textFieldHost, BorderLayout.CENTER);
		textFieldHost.setColumns(10);

		JPanel panelMensajes = new JPanel();
		panelPrincipal.add(panelMensajes, BorderLayout.CENTER);
		panelMensajes.setLayout(new BorderLayout(0, 0));

		areaMensajes = new JTextArea();
		areaMensajes.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		areaMensajes.setFocusable(false);
		areaMensajes.setEnabled(false);
		areaMensajes.setEditable(false);

		JScrollPane scrollMensajes = new JScrollPane(areaMensajes);
		scrollMensajes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelMensajes.add(scrollMensajes, BorderLayout.CENTER);

		JPanel panelTexto = new JPanel();
		panelPrincipal.add(panelTexto, BorderLayout.SOUTH);
		panelTexto.setLayout(new BorderLayout(0, 0));

		textFieldMensaje = new JTextField();
		textFieldMensaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldMensaje.getText().length() < 47 || e.getKeyCode() == 10) {
				} else {
					e.setKeyChar('\0');
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					enviaMensaje();
			}
		});
		textFieldMensaje.setEnabled(false);
		panelTexto.add(textFieldMensaje, BorderLayout.CENTER);
		textFieldMensaje.setColumns(10);

		btnEnviarMensaje = new JButton("Enviar");
		btnEnviarMensaje.setFocusable(false);
		btnEnviarMensaje.setEnabled(false);
		btnEnviarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviaMensaje();
			}
		});
		panelTexto.add(btnEnviarMensaje, BorderLayout.EAST);

		JPanel panelConectados = new JPanel();
		getContentPane().add(panelConectados, BorderLayout.EAST);
		panelConectados.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panelConectados.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelNombre = new JPanel();
		panel.add(panelNombre, BorderLayout.NORTH);
		panelNombre.setLayout(new BorderLayout(0, 0));

		JPanel panelTextoNombre = new JPanel();
		panelNombre.add(panelTextoNombre, BorderLayout.NORTH);
		panelTextoNombre.setLayout(new BorderLayout(0, 0));

		lblNombre = new JLabel("Nombre");
		panelTextoNombre.add(lblNombre, BorderLayout.NORTH);
		lblNombre.setEnabled(false);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

		textFieldNombre = new JTextField();
		panelTextoNombre.add(textFieldNombre, BorderLayout.SOUTH);
		textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldNombre.getText().length() < 16 || e.getKeyCode() == 10) {
				} else {
					e.setKeyChar('\0');
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10)
					cambiaNombre();
			}
		});
		textFieldNombre.setEnabled(false);
		textFieldNombre.setColumns(10);

		btnCambiarNombre = new JButton("Cambiar Nombre");
		btnCambiarNombre.setFocusable(false);
		btnCambiarNombre.setEnabled(false);
		btnCambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaNombre();
			}
		});
		panelNombre.add(btnCambiarNombre, BorderLayout.CENTER);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setEnabled(false);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		panelNombre.add(lblUsuarios, BorderLayout.SOUTH);

		areaConectados = new JTextArea();
		areaConectados.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		areaConectados.setEnabled(false);
		areaConectados.setMargin(new Insets(5, 10, 5, 10));
		areaConectados.setFocusable(false);
		areaConectados.setEditable(false);

		JScrollPane scrollConectados = new JScrollPane(areaConectados);
		panel.add(scrollConectados, BorderLayout.CENTER);

		setVisible(true);

	}

	private static final long serialVersionUID = 1L;
	private static final int PUERTO = 39876;
	private HashMap<String, String> conectados;

	private JTextField textFieldMensaje;
	private JTextField textFieldNombre;
	private Socket conexion;
	public boolean abierto;
	public JTextArea areaMensajes;
	private JTextField textFieldHost;
	private PrintStream salida;
	private String nombre;
	private JButton btnEnviarMensaje;

	private HiloCliente hiloLector;
	private JTextArea areaConectados;
	private JButton btnCambiarNombre;
	private JLabel lblNombre;
	private JLabel lblUsuarios;

	private void setEstamosconectados(boolean conectados) {
		btnEnviarMensaje.setEnabled(conectados);
		btnCambiarNombre.setEnabled(conectados);
		areaConectados.setEnabled(conectados);
		areaMensajes.setEnabled(conectados);
		textFieldMensaje.setEnabled(conectados);
		textFieldNombre.setEnabled(conectados);
		lblNombre.setEnabled(conectados);
		lblUsuarios.setEnabled(conectados);
	}

	@SuppressWarnings("deprecation")
	void conectar() {
		String host = textFieldHost.getText();
		conectados = new HashMap<String,String>();
		if (host.isEmpty()) {
			textFieldHost.setBackground(Color.RED);
			textFieldHost.grabFocus();
		} else {
			textFieldHost.setBackground(Color.WHITE);
			try {
				if (conexion != null) {
					hiloLector.stop();
					conexion.close();
				}
				conexion = new Socket(host, PUERTO);
				salida = new PrintStream(conexion.getOutputStream());
				hiloLector = new HiloCliente(conexion, this);
				setTitle("Chat 3000 - Conectado: " + host);
				addTexto("Conexion establecida con " + host);
				setEstamosconectados(true);
				enviaMensaje("f:" + nombre);
				textFieldMensaje.grabFocus();
				new HiloMuerto(this);
			} catch (UnknownHostException e) {
				mensajeDeError("El host indicado no existe");
				textFieldHost.grabFocus();
			} catch (ConnectException e) {
				mensajeDeError("El host indicado no es un servidor valido");
				textFieldHost.grabFocus();
			} catch (IOException e) {
				mensajeDeError("Ha ocurrido un error inesperado");
				e.printStackTrace();
				textFieldHost.grabFocus();
			}
		}

	}

	private void enviaMensaje() {
		if (!textFieldMensaje.getText().isEmpty()) {
			try {
				String mensaje = textFieldMensaje.getText();
				textFieldMensaje.setText("");
				salida.println("m:" + nombre + ": " + mensaje);
				addTexto(mensaje, true);
				textFieldMensaje.grabFocus();
			} catch (Exception e) {
				e.printStackTrace();
				btnEnviarMensaje.setEnabled(false);
			}
		}
	}

	public void enviaMensaje(String mensaje) {
		try {
			salida.println(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void limpiaNombres() {
		conectados.clear();
	}

	void listaConectados() {
		areaConectados.setText("");
		for (String s : conectados.values())
			areaConectados.append(s + "\n");
		areaConectados.repaint();
	}

	void pideNombres() {
		enviaMensaje("f:" + nombre);
	}

	private void cambiaNombre() {
		if (textFieldNombre.getText().isEmpty()) {
			nombre = "Anonimo";
		} else {
			nombre = textFieldNombre.getText();
		}
		addTexto("Nombre cambiado a " + nombre, true);
		enviaNombre();
		textFieldMensaje.grabFocus();
	}

	private void mensajeDeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Atenci�n", 0);
	}

	protected void servidorCaido(String mensaje) {
		setTitle("Chat 3000 - Desconectado");
		setEstamosconectados(false);
		mensajeDeError(mensaje);
	}

	public void addTexto(String texto) {
		addTexto(texto, false);
	}

	private void addTexto(String texto, boolean soyYo) {
		if (!soyYo) {
			areaMensajes.append(texto + "\n");
		} else {
			areaMensajes.append("\t\t" + texto + "\n");
		}
	}

	public static void main(String[] args) {
		new ClienteUI();
	}

	public void enviaNombre() {
		enviaMensaje("n:"+nombre);
	}
	
	synchronized public void guardaNombre(String nombre, String host) {
		conectados.put(host, nombre);
		
	}
}
