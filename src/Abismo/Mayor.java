package Abismo;

import java.util.Scanner;

public class Mayor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long num1;
		long num2;

		try {
			System.out.println("Introduzca el primer numero: ");
			num1 = pideNumero(in);
			System.out.println("Introduzca el segundo numero: ");
			num2 = pideNumero(in);
			System.out.println("El mayor es: " + Math.max(num1, num2));
		} catch (Exception e) {
			System.exit(3);
		}
		in.close();
	}

	private static long pideNumero(Scanner in) {
		long num1;
		num1 = in.nextLong();
		return num1;
	}
}