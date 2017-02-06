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
				System.out.println("\t\"may\": si quiere toda la frase en may�scula");
				System.out.println("\t\"min\": si quiere toda la frase en min�scula");
				System.out.println(
						"\t\"inic\": si quiere en may�scula la primera letra de cada palabra de la frase en may�scula");
				opc = pedir.nextLine();

				if (opc.equals("may")) {
					fraseCamb = frase.toUpperCase();
					System.out.println("\t\tLa frase " + frase + " en may�sculas es  \"" + fraseCamb + "\"");

				} else if (opc.equals("min")) {
					fraseCamb = frase.toLowerCase();
					System.out.println("\t\tLa frase " + frase + " en min�sculas es  \"" + fraseCamb + "\"");
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
							"\t\tLa frase " + frase + ", con la primera letra en may�scula, es \"" + fraseCamb + "\"");

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
