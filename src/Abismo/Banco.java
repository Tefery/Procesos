package Abismo;
public class Banco {
	
	float dinero;
	
	public Banco() {
		this(0);
	}
	
	public Banco(float d) {
		dinero = d;
	}
	
	synchronized public float consultaSaldo() {
		return dinero;
	}
	
	synchronized public void sacaDinero(float cant) {
		if(cant <= dinero) {
			dinero -= cant;
		} else {
			System.err.println("No tiene suficiente saldo");
		}
	}
	
	synchronized public Float ingresaDinero(float cant) {
		if(dinero == Float.MAX_VALUE) {
			System.out.println("La cuenta está llena, no se ha podido realizar la operación");
			return new Float(cant);
		}
		if((long)cant+(long)dinero <= Float.MAX_VALUE) {
			dinero += cant;
			return new Float(0);
		} else {
			Float maximo = Float.MAX_VALUE;
			maximo -= dinero;
			float sobrante = cant;
			cant -= maximo;
			sobrante -= cant;
			dinero = Float.MAX_VALUE;
			System.out.println("Has superado el limite de la cuenta, se han podido ingresar: "+cant+"€");
			return new Float(sobrante);
		}
	}
}