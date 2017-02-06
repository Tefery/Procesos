package Abismo;

import java.util.Scanner;

public class SoloPrimos {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Inserte el numero: ");
		pedirnumeros(in);
		in.close();
	}

	private static void pedirnumeros(Scanner in) {
		try {
			String snumero;
			do {
				snumero = in.nextLine();
				int numero = Integer.parseInt(snumero);
				numero = Math.abs(numero);
				boolean primo = true;
				for (int i = numero / 2; i > 1; i--) {
					if (numero % i == 0) {
						primo = false;
						System.out.println("NOESPRIMO");
						break;
					}
				}
				if (primo)
					System.out.println(snumero);
			} while (snumero != null);
		} catch (Exception e) {
			System.out.println("ERROR");
			pedirnumeros(in);
		}
	}
}