package Abismo;



import java.util.Scanner;

public class Divisores {
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		boolean seguimos = true;
		while (seguimos) {
			System.out.println("Introduce un numero");
			try {
				long num = in.nextLong();
				num = Math.abs(num);
				divisores(num);
			} catch (Exception e) {
				break;
			}
			// seguimos = pregunta();
		}
		System.err.println("No se pueden introducir letras");
		System.exit(3);
	}

	private static void divisores(long num) {
		for (long i = num/2; i > 1; i--) {
			if(num%i==0){
				System.out.println(i);
			}
		}
	}

	public static boolean pregunta() {
		System.out.println("Seguimos? S/N");
		String res = in.nextLine().toUpperCase();
		if (res.equals("N"))
			return false;
		if (res.equals("S"))
			return true;
		return pregunta();
	}
}
