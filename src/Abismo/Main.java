package Abismo;

public class Main {
	public static void main(String[] args) {
		Surtidor surtidor = new Surtidor();
		RecargaSurtidor rSurtidor = new RecargaSurtidor(surtidor);
		Cliente cli1 = new Cliente("1",surtidor.txtPaneCli1,surtidor,rSurtidor);
		Cliente cli2 = new Cliente("2",surtidor.txtPaneCli2,surtidor,rSurtidor);
		surtidor.setVisible(true);
		rSurtidor.setVisible(true);
		cli1.start();
		cli2.start();
	}
}
