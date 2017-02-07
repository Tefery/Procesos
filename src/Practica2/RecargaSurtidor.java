package Practica2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecargaSurtidor extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JTextField textFieldRecarga;
	
	private Surtidor s;
	public JButton btnRecarga;
	
	public RecargaSurtidor(Surtidor s) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.s = s;
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblRecargar = new JLabel("Recargar:");
		lblRecargar.setBounds(10, 14, 64, 14);
		getContentPane().add(lblRecargar);
		
		textFieldRecarga = new JTextField();
		textFieldRecarga.setBounds(64, 11, 337, 20);
		getContentPane().add(textFieldRecarga);
		textFieldRecarga.setColumns(10);
		
		btnRecarga = new JButton("Recarga");
		btnRecarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recarga();
			}
		});
		btnRecarga.setBounds(411, 10, 106, 23);
		getContentPane().add(btnRecarga);
		setSize(s.getSize().width,70);
		setLocation(s.getLocation().y,s.getLocation().x+s.getSize().height);
		btnRecarga.setEnabled(false);
		textFieldRecarga.setEnabled(false);
	}
	
	public void cambiaVis() {
		if(s.gas>=35000) {
			btnRecarga.setEnabled(false);
			textFieldRecarga.setEnabled(false);
		} else {
			btnRecarga.setEnabled(true);
			textFieldRecarga.setEnabled(true);
		}
	}
	
	private void recarga() {
		try {
		int cantidad = Integer.parseInt(textFieldRecarga.getText());
		s.aniadeGas(cantidad);
		} catch (NumberFormatException ex) {
			textFieldRecarga.setText("");
			cambiaVis();
		}
	}
}
