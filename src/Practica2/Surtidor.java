package Practica2;

import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Surtidor extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public JTextArea txtPaneCli1;
	public JTextArea txtPaneCli2;
	public JTextArea txtPanTrab;
	public JLabel lblGas;
	public JLabel lblGasCons;
	
	public Surtidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblSurtidor = new JLabel("Surtidor 1");
		lblSurtidor.setBounds(10, 101, 82, 14);
		getContentPane().add(lblSurtidor);
		
		JLabel lblCliente = new JLabel("Surtidor 2");
		lblCliente.setBounds(10, 188, 82, 14);
		getContentPane().add(lblCliente);
		
		JLabel lblTrabajador = new JLabel("Trabajador");
		lblTrabajador.setBounds(10, 292, 82, 14);
		getContentPane().add(lblTrabajador);
		
		txtPaneCli1 = new JTextArea();
		txtPaneCli1.setLineWrap(true);
		txtPaneCli1.setWrapStyleWord(true);
		txtPaneCli1.setMargin(new Insets(2, 5, 2, 5));
		txtPaneCli1.setEditable(false);
		JScrollPane Scroll1 = new JScrollPane(txtPaneCli1);
		Scroll1.setBounds(102, 70, 414, 74);
		Scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroll1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(Scroll1);
		
		txtPaneCli2 = new JTextArea();
		txtPaneCli2.setEditable(false);
		JScrollPane Scroll2 = new JScrollPane(txtPaneCli2);
		Scroll2.setBounds(102, 157, 414, 74);
		Scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(Scroll2);
		
		txtPanTrab = new JTextArea();
		txtPanTrab.setEditable(false);
		JScrollPane Scroll3 = new JScrollPane(txtPanTrab);
		Scroll3.setBounds(103, 242, 414, 120);
		Scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Scroll3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(Scroll3);
		
		JLabel lblGasolina = new JLabel("Gasolina:");
		lblGasolina.setBounds(10, 11, 138, 14);
		getContentPane().add(lblGasolina);
		
		JLabel lblGasolinaConsumida = new JLabel("Gasolina consumida:");
		lblGasolinaConsumida.setBounds(10, 36, 138, 14);
		getContentPane().add(lblGasolinaConsumida);
		
		lblGas = new JLabel("0");
		lblGas.setBounds(155, 11, 46, 14);
		getContentPane().add(lblGas);
		
		lblGasCons = new JLabel("0");
		lblGasCons.setBounds(155, 36, 46, 14);
		getContentPane().add(lblGasCons);
	}
	
	synchronized public boolean cambiaGas(int cantidad) {
		lblGas.setText(String.valueOf(Integer.parseInt(lblGas.getText())+cantidad));
		if(Integer.parseInt(lblGas.getText())>=35000) {
			lblGas.setText("35000");
		} else if (Integer.parseInt(lblGas.getText()) < 0) {
			return true;
		}
		return false;
	}
}
