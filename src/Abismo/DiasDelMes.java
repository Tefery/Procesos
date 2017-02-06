package Abismo;


public class DiasDelMes {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Se esperaban solo dos argumentos numericos (MES, AÑO).");
			System.exit(2);
		} else {
			int i = 0, anio = 0;
			try {
				i = Integer.parseInt(args[0]) - 1;
				anio = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.err.println("Se esperaban argumentos numericos (MES, AÑO).");
				System.exit(3);
			}
			
			if(i > 11 || i < 0 || anio < 1){
				System.err.println("Numeros fuera de rango");
				System.exit(4);
			}

			String Meses[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
					"Octubre", "Noviembre", "Diciembre" };
			int dias;
			dias = 31;
			if (i == 1)
				if (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0))
					dias = 29;
				else
					dias = 28;
			else if (i == 3 || i == 5 || i == 8 || i == 10)
				dias = 30;
			System.out.println("El mes de " + Meses[i] + " de " + anio + " Tiene " + dias + " Días.\n");
		}
	}
}