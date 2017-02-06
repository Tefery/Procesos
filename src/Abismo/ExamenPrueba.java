package Abismo;

import java.util.Scanner;

public class ExamenPrueba {

	public static void main(String[] args) {
		Scanner pedir = new Scanner(System.in);
		String opc, frase, fraseCamb;
		char[] caracteres;

		if (args[0].equals("1")) {

			do {
				System.out.println("\t\tIndique la frase: ");
				frase = pedir.nextLine();

				System.out.println("Indique un  ");
				System.out.println("\t\"may\": si quiere toda la frase en mayúscula");
				System.out.println("\t\"min\": si quiere toda la frase en minúscula");
				System.out.println(
						"\t\"inic\": si quiere en mayúscula la primera letra de cada palabra de la frase en mayúscula");
				opc = pedir.nextLine();

				if (opc.equals("may")) {
					fraseCamb = frase.toUpperCase();
					System.out.println("\t\tLa frase " + frase + " en mayúsculas es  \"" + fraseCamb + "\"");

				} else if (opc.equals("min")) {
					fraseCamb = frase.toLowerCase();
					System.out.println("\t\tLa frase " + frase + " en minúsculas es  \"" + fraseCamb + "\"");
				}

				else {
					fraseCamb = frase.toLowerCase();
					caracteres = fraseCamb.toCharArray();

					caracteres[0] = Character.toUpperCase(caracteres[0]);

					for (int i = 0; i < fraseCamb.length(); i++) {
						if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') {
							caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
						}
					}

					fraseCamb = String.valueOf(caracteres);

					System.out.println(
							"\t\tLa frase " + frase + ", con la primera letra en mayúscula, es \"" + fraseCamb + "\"");

				}
			} while (frase != null);
		} else {
			for (char letra = 'A'; letra <= 'Z'; letra++) {
				System.out.println("\t\tLetra:  " + letra);
			}
		}
		pedir.close();
	}

}
