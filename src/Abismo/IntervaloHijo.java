package Abismo;

import java.util.Scanner;

public class IntervaloHijo {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		intervalo(in);
	}

	private static void intervalo(Scanner in) {
		try {
			System.out.println("Introduzca el primer numero: ");
			int numero1 = in.nextInt();
			System.out.println("Introduzca el segundo numero: ");
			int numero2 = in.nextInt();
			if(numero1>numero2) {
				System.out.println("El segundo numero no puede ser mayor que el primero");
				intervalo(in);
			} else {
				System.out.println("("+numero1+", "+numero2+")");
			}
		} catch (Exception e) {
			System.out.println("Introduzca solo numeros");
			in.nextLine();
			intervalo(in);
		}
	}
}