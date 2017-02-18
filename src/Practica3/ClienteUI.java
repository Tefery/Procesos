package Practica3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

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
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		panelBotones.add(btnConectar, BorderLayout.EAST);

		textFieldHost = new JTextField();
		panelBotones.add(textFieldHost, BorderLayout.CENTER);
		textFieldHost.setColumns(10);

		JPanel panelMensajes = new JPanel();
		panelPrincipal.add(panelMensajes, BorderLayout.CENTER);
		panelMensajes.setLayout(new BorderLayout(0, 0));

		areaMensajes = new JTextArea();
		areaMensajes.setEditable(false);

		JScrollPane scrollMensajes = new JScrollPane(areaMensajes);
		scrollMensajes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelMensajes.add(scrollMensajes, BorderLayout.CENTER);

		JPanel panelTexto = new JPanel();
		panelPrincipal.add(panelTexto, BorderLayout.SOUTH);
		panelTexto.setLayout(new BorderLayout(0, 0));

		textFieldMensaje = new JTextField();
		panelTexto.add(textFieldMensaje, BorderLayout.CENTER);
		textFieldMensaje.setColumns(10);

		JButton btnEnviarMensaje = new JButton("Enviar");
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

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panelNombre.add(lblNombre, BorderLayout.NORTH);

		textFieldNombre = new JTextField();
		panelNombre.add(textFieldNombre, BorderLayout.CENTER);
		textFieldNombre.setColumns(10);

		JButton btnCambiarNombre = new JButton("Cambiar Nombre");
		panelNombre.add(btnCambiarNombre, BorderLayout.SOUTH);

		JTextArea areaConectados = new JTextArea();
		areaConectados.setEditable(false);

		JScrollPane scrollConectados = new JScrollPane(areaConectados);
		panel.add(scrollConectados, BorderLayout.CENTER);

	}

	private static final long serialVersionUID = 1L;
	private static final int PUERTO = 39876;

	private JTextField textFieldMensaje;
	private JTextField textFieldNombre;
	public Socket conexion;
	public boolean abierto;
	private JTextArea areaMensajes;
	private JTextField textFieldHost;
	private PrintStream salida;

	void conectar() {
		if (textFieldHost.getText().isEmpty()) {
			textFieldHost.setBackground(Color.RED);
		} else {
			textFieldHost.setBackground(Color.WHITE);
			try {
				if (conexion != null)
					conexion.close();
				conexion = new Socket(textFieldHost.getText(), PUERTO);
				salida = new PrintStream(conexion.getOutputStream());
				arrancarLector(textFieldHost.getText());
			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, "El host indicado no existe", "Atención", 0);
			} catch (ConnectException e) {
				JOptionPane.showMessageDialog(null, "El host indicado no es un servidor valido", "Atención", 0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado", "Atención", 0);
				e.printStackTrace();
			}
		}
	}

	void arrancarLector(String host) {
		addTexto("Conexion establecida con " + host);
		new Runnable() {
			@Override
			public void run() {
				BufferedReader entrada;
				try {
					entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
					String mensaje;
					while (abierto) {
						mensaje = entrada.readLine();
						areaMensajes.append(mensaje);
					}
					conexion.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}

	private void enviaMensaje() {
		if (!textFieldMensaje.getText().isEmpty()) {
			String mensaje = textFieldMensaje.getText();
			textFieldMensaje.setText("");
			salida.println(mensaje);
			addTexto(mensaje,true);
		}
	}

	private void addTexto(String texto) {
		addTexto(texto, false);
	}

	private void addTexto(String texto, boolean soyYo) {
		areaMensajes.append(texto + "\n");
	}
	
	public static void main(String[] args) {
		ClienteUI cli = new ClienteUI();
		cli.setVisible(true);
	}
}
