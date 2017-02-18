package temp;

import Practica3.ClienteUI;

public class Cliente extends Thread {
	
	private ClienteUI clienteUI;
	
	public Cliente(ClienteUI clienteUI){
		this.clienteUI = clienteUI;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
}
