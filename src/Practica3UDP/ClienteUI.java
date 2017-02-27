package Practica3UDP;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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

		try {
			infoServidor = InetAddress.getByName(broadcastMask);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		abierto = true;
		nombre = "Anonimo";
		conectados = new HashMap<String, String>();
		conectar();

		setTitle("Chat 3000 - Conectado");
		setSize(558, 515);

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelMensajes = new JPanel();
		panelPrincipal.add(panelMensajes, BorderLayout.CENTER);
		panelMensajes.setLayout(new BorderLayout(0, 0));

		areaMensajes = new JTextArea();
		areaMensajes.setFocusable(false);
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
		panelTexto.add(textFieldMensaje, BorderLayout.CENTER);
		textFieldMensaje.setColumns(10);

		btnEnviarMensaje = new JButton("Enviar");
		btnEnviarMensaje.setFocusable(false);
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

		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panelNombre.add(lblNombre, BorderLayout.NORTH);

		textFieldNombre = new JTextField();
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
		panelNombre.add(textFieldNombre, BorderLayout.CENTER);
		textFieldNombre.setColumns(10);

		btnCambiarNombre = new JButton("Cambiar Nombre");
		btnCambiarNombre.setFocusable(false);
		btnCambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiaNombre();
			}
		});
		panelNombre.add(btnCambiarNombre, BorderLayout.SOUTH);

		areaConectados = new JTextArea();
		areaConectados.setMargin(new Insets(10, 5, 10, 5));
		areaConectados.setFocusable(false);
		areaConectados.setEditable(false);

		JScrollPane scrollConectados = new JScrollPane(areaConectados);
		panel.add(scrollConectados, BorderLayout.CENTER);

		setVisible(true);

	}

	private void conectar() {
		try {
			conexion = new DatagramSocket(PUERTO);
			byte[] mensaj = new byte[64];
			paquete = new DatagramPacket(mensaj, mensaj.length);
			hiloLector = new HiloCliente(conexion, paquete, this, InetAddress.getLocalHost().getHostAddress());
		} catch (BindException e) {
			mensajeDeError("La aplicación ya está en ejecución");
			System.exit(0);
		} catch (SocketException e1) {
			mensajeDeError("ERROR: No ha sido posible establecer la conexión, reinicie la aplicación");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;
	private static final int PUERTO = 39876;
	private String broadcastMask = "255.255.255.255";
	private InetAddress infoServidor;

	public boolean abierto;
	private HiloCliente hiloLector;
	private DatagramSocket conexion;
	private DatagramPacket paquete;
	private String nombre;
	private HashMap<String, String> conectados;

	private JTextField textFieldMensaje;
	private JTextField textFieldNombre;
	public JTextArea areaMensajes;
	private JButton btnEnviarMensaje;
	private JTextArea areaConectados;
	private JButton btnCambiarNombre;
	private JLabel lblNombre;

	private void enviaMensaje() {
		if (!textFieldMensaje.getText().isEmpty()) {
			try {
				String mensaje = textFieldMensaje.getText();
				textFieldMensaje.setText("");
				enviaMensaje(mensaje);
				addTexto(mensaje, true);
				textFieldMensaje.grabFocus();
			} catch (Exception e) {
				e.printStackTrace();
				btnEnviarMensaje.setEnabled(false);
			}
		}
	}

	private void enviaMensaje(String mensaje) {
		mensaje = completaString("m:" + nombre + ": " + mensaje);
		paquete = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, infoServidor, PUERTO);
		try {
			conexion.send(paquete);
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

	private String completaString(String mensaje) {
		while (mensaje.getBytes().length < 64) {
			mensaje += " ";
		}
		mensaje = mensaje.substring(0, 64);
		return mensaje;
	}

	private void cambiaNombre() {
		if (textFieldNombre.getText().isEmpty()) {
			nombre = "Anonimo";
		} else {
			nombre = textFieldNombre.getText();
		}
		addTexto("Nombre cambiado a " + nombre, true);
		textFieldMensaje.grabFocus();
	}

	public void cambiaNombre(String host, String nombre) {
		if (nombre != null)
			conectados.put(host, nombre);
		else
			conectados.remove(host);
		listaConectados();
	}

	private void listaConectados() {
		areaConectados.setText("");
		for (String s : conectados.values())
			areaConectados.append(s + "/n");
	}

	private void mensajeDeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Atención", 0);
	}

	public void addTexto(String texto) {
		addTexto(texto, false);
	}

	private void addTexto(String texto, boolean soyYo) {
		areaMensajes.append(texto + "\n");
	}

	public String GenerateBroadcastMask(String addr) {

		if (broadcastMask != null)
			return broadcastMask;

		String localIP = addr;

		int dotPos = localIP.indexOf('.');
		if (dotPos != -1) {

			String ipStr = localIP.substring(0, dotPos);
			int ipVal = Integer.valueOf(ipStr).intValue();

			if (ipVal <= 127) {

				// direccion clase A

				broadcastMask = "" + ipVal + ".255.255.255";
			} else if (ipVal <= 191) {

				// Direccion clase B

				dotPos++;
				while (localIP.charAt(dotPos) != '.' && dotPos < localIP.length())
					dotPos++;

				if (dotPos < localIP.length())
					broadcastMask = localIP.substring(0, dotPos) + ".255.255";
			} else if (ipVal <= 223) {

				// Direccion clase C

				dotPos++;
				int dotCnt = 1;

				while (dotCnt < 3 && dotPos < localIP.length()) {

					if (localIP.charAt(dotPos++) == '.')
						dotCnt++;
				}

				if (dotPos < localIP.length())
					broadcastMask = localIP.substring(0, dotPos - 1) + ".255";
			}
		}

		if (broadcastMask == null) {

			broadcastMask = "255.255.255.255";
		}

		return broadcastMask;
	}

	public static void main(String[] args) {
		new ClienteUI();
	}
}
