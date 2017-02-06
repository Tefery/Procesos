package Abismo;
@SuppressWarnings("deprecation")
public class VisualizarMensajes extends Thread {

	private String colorMensaje;
	private int lineas;
	private boolean visPrimerMensaje = false;
	private boolean continuo = true;

	private VisualizarMensajes otroHilo = null;

	VisualizarMensajes(String color, int lineas) {
		colorMensaje = color;
		this.lineas = lineas;
	}
	
	VisualizarMensajes(String color) {
		this(color, 500);
	}

	public void setOtroHilo(VisualizarMensajes hilo) {
		otroHilo = hilo;
	}

	public void continuaLaEjecucion() {
		continuo = true;
	}
	
	public void paraLaEjecucion() {
		continuo = false;
	}

	@Override
	public void run() {
		int i = 0;
		while (i <= lineas && continuo) {
			if (continuo) {
				continuo = false;
				if (!continuo)
					break;
				if (this.getName().equals("Hilo2") && !otroHilo.visPrimerMensaje) {
					this.suspend();
				}
				System.out.println(colorMensaje + "Mensaje " + i);
				otroHilo.resume();
				visPrimerMensaje = true;
				if (otroHilo.isAlive())
					this.suspend();
				try {
					sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!continuo)
					break;
				i++;
				if(i != lineas) {
					continuo = false;
					otroHilo.paraLaEjecucion();
				}
			}
		}
		otroHilo.resume();
	}
}