package ClienteFTP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PideString extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2595473247345782347L;
	private JTextField txtNombre;
	private FTPui v;
	public String nombreOriginal = "";
	public PideString(FTPui vv) {
		this.v = vv;
		setSize(new Dimension(350, 73));
		setMinimumSize(new Dimension(350, 73));
		setMaximumSize(new Dimension(350, 73));
		setType(Type.UTILITY);
		setResizable(false);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNuevoNombre = new JLabel(" Nuevo nombre: ");
		panel.add(lblNuevoNombre, BorderLayout.WEST);
		
		txtNombre = new JTextField(nombreOriginal);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel_1.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.aceptaRetorno = true;
				v.retorno = txtNombre.getText();
				setVisible(false);
			}
		});
		panel_1.add(btnAceptar);
		setModal(true);
	}
	public void cambiaNombreOriginal(String ad) {
		nombreOriginal = ad;
		txtNombre.setText(nombreOriginal);
	}
}
