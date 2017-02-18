package Practica3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import apuntes.HiloEnvia;
import apuntes.HiloEscuchaCliente;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Cliente extends JFrame {
	public Cliente() {
		setTitle("Chat 3000");

		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		panelPrincipal.add(panelBotones, BorderLayout.NORTH);
		panelBotones.setLayout(new BorderLayout(0, 0));

		JButton btnConectar = new JButton("Conectar");
		panelBotones.add(btnConectar, BorderLayout.EAST);

		JButton btnDesconectar = new JButton("Desconectar");
		panelBotones.add(btnDesconectar, BorderLayout.WEST);

		JLabel lblEstadoRed = new JLabel("Estado: Desconectado");
		lblEstadoRed.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblEstadoRed, BorderLayout.CENTER);

		JPanel panelMensajes = new JPanel();
		panelPrincipal.add(panelMensajes, BorderLayout.CENTER);
		panelMensajes.setLayout(new BorderLayout(0, 0));

		JTextArea areaMensajes = new JTextArea();
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

		JScrollPane scrollConectados = new JScrollPane(areaConectados);
		panel.add(scrollConectados, BorderLayout.CENTER);
	}

	private static final long serialVersionUID = 1L;
	private JTextField textFieldMensaje;
	private JTextField textFieldNombre;

	public static void main(String[] args) {
		final int PUERTO = 2662;
		String NOMBRE_SERVIDOR = "localhost";

		System.out.println("Vamos a intentar conectarnos con el servidor");

		try {
			Scanner in = new Scanner(System.in);
			Socket conex = new Socket(NOMBRE_SERVIDOR, PUERTO);
			System.out.println("conseguido");
			System.out.println("Introduce el nombre de usuario");
			String nombre = in.nextLine();
			new HiloEscuchaCliente(conex);
			new HiloEnvia(conex, nombre);
			in.close();
		} catch (UnknownHostException e) {
			System.err.println("No se ha encontrado el host: " + NOMBRE_SERVIDOR);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error inesperado");
			e.printStackTrace();
		}
	}
}
