package Abismo;

public class SoloPrimos2 {
	public static void main(String[] args) {
		if (args.length == 2) {
			try {
				int numero1 = Integer.parseInt(args[0]);
				int numero2 = Integer.parseInt(args[1]);
				if(numero1<=numero2){
					for (int i = numero1; i <= numero2; i++){
						System.out.println("Divisores del numero "+i);
						for (int e = Math.abs(i)/2; e > 0; e--) {
							if(i % e == 0)
								System.out.println(e);
						}
					}
				} else
					System.out.println("El intervalo es incorrecto");
			} catch (Exception e) {
				System.out.println("Se esperaban solo valores numericos enteros");
			}
		} else
			System.out.println("Se esperaban dos argumentos");
	}
}