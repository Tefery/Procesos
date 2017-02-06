package Abismo;

import java.util.Scanner;

public class NumerosPrimos {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean esNumero = false;
		do {
			System.out.println("Introduce un numero");
			try {
				long num = Long.parseLong(in.nextLine());
				divisores(num);
				esNumero = true;
				System.out.println("$ERROR");
			} catch (Exception e) {
				System.out.println();
				System.out.println("Mete solo numeros: ");
				System.out.println("$ERROR");
			}
		} while (!esNumero);
		in.close();
	}

	private static void divisores(long num) {
		for (long i = num / 2; i > 1; i--) {
			if (num % i == 0) {
				System.out.println(i);
			}
		}

	}
}
