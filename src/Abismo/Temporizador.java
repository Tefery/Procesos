package Abismo;

public class Temporizador extends Thread {

	private int tiempo;
	private boolean salimos;

	/**
	 * Crea un temporizador. Cuando termina el tiempo, indicado en segundos,
	 * termina la ejecución.
	 * 
	 * @param tiemp
	 *            Tiempo en segundos.
	 */
	Temporizador(int tiemp) {
		tiempo = tiemp;
		salimos = false;
	}

	private void ticks(int tiempo) throws InterruptedException {
		for (int i = 0; i < tiempo * 8; i++) {
			Thread.sleep(125);
			if (salimos)
				break;
		}
	}

	public void finaliza() {
		salimos = true;
	}

	@Override
	public void run() {
		try {
			ticks(tiempo);
			if (!salimos) {
				System.out.println();
				System.out.println("se te ha acabado el tiempo");
				System.exit(1);
			}
		} catch (InterruptedException e) {
		}
	}

	public int getTiempo() {
		return tiempo;
	}
}
