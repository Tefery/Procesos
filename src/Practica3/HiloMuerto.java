package Practica3;

public class HiloMuerto extends Thread {

	ClienteUI clienteUI;

	public HiloMuerto(ClienteUI clienteUI) {
		this.clienteUI = clienteUI;
		this.setDaemon(true);
		start();
	}

	@Override
	public void run() {
		while (true) {
			clienteUI.limpiaNombres();
			clienteUI.pideNombres();
			try {
				sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			clienteUI.listaConectados();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
