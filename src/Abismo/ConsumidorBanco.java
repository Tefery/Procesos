package Abismo;

public class ConsumidorBanco extends Thread{
	
	String nombre;
	Banco banco;
	
	public ConsumidorBanco(Banco b, String nom) {
		nombre = nom;
		banco = b;
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			banco.sacaDinero(50);
			System.out.println("Se han ingresado 50€, hay: "+banco.consultaSaldo()+"€");
		}
	}
}