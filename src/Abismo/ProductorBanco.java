package Abismo;

public class ProductorBanco extends Thread {

	String nombre;
	Banco banco;
	
	public ProductorBanco(Banco b, String nom) {
		nombre = nom;
		banco = b;
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			banco.sacaDinero(60);
			System.out.println("Se han sacado 60€, quedan: "+banco.consultaSaldo()+"€");
		}
	}
}
