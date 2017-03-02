package Practica3;


public class HombreMuerto extends Thread{
	
	ClienteUI clienteUI;
	
	public HombreMuerto(ClienteUI clienteUI) {
		this.clienteUI = clienteUI;
		this.setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			//clienteUI.limpiaNombres();
			//clienteUI.pideNombres();
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
