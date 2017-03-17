package chatRedLocal;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.io.IOException;
import java.net.BindException;

public class ServidorUI extends JFrame {

	private static final long serialVersionUID = -2858342351946898291L;
	private JTextArea textArea;

	public ServidorUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setMargin(new Insets(5, 10, 5, 10));

		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		setSize(350, 500);

		setVisible(true);
	}

	public void addText(String texto) {
		textArea.append(texto + "\n");
	}

	public static void main(String[] args) {
		ServidorUI ventana = new ServidorUI();
		final int puerto = 39876;
		Servidor serv;
		try {
			serv = new Servidor(puerto, ventana);
			serv.start();
		} catch (BindException e) {
			JOptionPane.showMessageDialog(null, "El puerto " + puerto + " ya está en uso por otra aplicación", "ERROR",
					0);
			System.exit(1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error desconocido", "ERROR", 0);
			e.printStackTrace();
			System.exit(1);
		}

	}
}
