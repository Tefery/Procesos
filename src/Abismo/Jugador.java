package Abismo;

public class Jugador extends Thread {

	public int puntos;
	public String nombre;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		puntos = 0;
	}
	
	public void ganaUnPunto() {
		puntos += 1;
	}
}
