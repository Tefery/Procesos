package Practica2;

import javax.swing.JTextArea;

public class Cliente extends Thread{
	
	String nombre;
	JTextArea t;
	Surtidor s;
	RecargaSurtidor r;
	
	
	public Cliente(String nombre, JTextArea t, Surtidor s, RecargaSurtidor r) {
		this.nombre=nombre;
		this.t=t;
		this.s=s;
		this.r=r;
		
	}
	
	@Override
	public void run() {
		int cantidad;
		while(!s.cerrado) {
			try {
				sleep((int)(Math.random()*5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cantidad = (int)(Math.random()*2000);
			s.gastaGas(cantidad);
			r.cambiaVis();
		}
	}
}
