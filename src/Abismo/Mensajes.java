package Abismo;

public class Mensajes {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("Esto es el mensaje normal numero "+i);
			System.err.println("Esto es el mensaje erroneo numero "+i);
		}
	}
}
