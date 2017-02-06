package Abismo;

import java.util.Scanner;

public class TodoMayusculas {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Entroducde el mensaje");
		String mensaje= in.nextLine();
		System.out.println(mensaje.toUpperCase());
		in.close();
	}
}
