package Abismo;

public class MensajesPorHilos {
	public static void main(String[] args) {
		VisualizarMensajes hilo1 = new VisualizarMensajes(Colores.BLUE, 10);
		VisualizarMensajes hilo2 = new VisualizarMensajes(Colores.GREEN, 5);
		
		hilo1.setOtroHilo(hilo2);
		hilo2.setOtroHilo(hilo1);
		
		hilo1.setName("Hilo1");
		hilo2.setName("Hilo2");
		
		hilo1.start();
		hilo2.start();
	}
}