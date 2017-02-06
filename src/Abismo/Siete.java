package Abismo;

import java.util.ArrayList;

public class Siete extends Thread {

	private int veces;
	private String color;
	private boolean perdedor;
	private ArrayList<Siete> otrosJugadores;

	public Siete() {
		this("", 10, "Sin nombre");
	}

	public Siete(String color, int veces, String nombre) {
		this.color = color;
		this.veces = veces;
		setName(nombre);
		perdedor = false;
		otrosJugadores = new ArrayList<Siete>();
	}

	public Siete(String color, int veces, String nombre, ArrayList<Siete> elOtroHilo) {
		this(color, veces, nombre);
		aniadeJugadores(elOtroHilo);
	}

	private void aniadeJugadores(ArrayList<Siete> elOtroHilo) {
		for (Siete s : elOtroHilo) {
			addOtroJugador(s);
		}
	}

	@Override
	public void run() {
		try {
			sleep(otrosJugadores.size()*10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		allOtherAlive();
		for (int i = 0; i < veces && !perdedor; i++) {
			System.out.println(color + "Descanso " + (i + 1) + " del hilo " + getName());
			try {
				sleep(((int) (Math.random() * 11)) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (soyElUnicoVivo()) {
			ganador();
			System.out.println(color + getName() + " es el ganador!");
		}
	}

	private void ganador() {
		for (Siete siete : otrosJugadores) {
			siete.perdedor = true;
		}
	}

	private boolean soyElUnicoVivo() {
		for (Siete siete : otrosJugadores) {
			if (!siete.isAlive())
				return false;
		}
		return true;
	}

	private void allOtherAlive() {
		for (Siete s : otrosJugadores) {
			if (!s.isAlive())
				s.start();
		}
	}

	public int getVeces() {
		return veces;
	}

	public void setVeces(int veces) {
		this.veces = veces;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isPerdedor() {
		return perdedor;
	}

	private void setMisJugadores(ArrayList<Siete> otrosJugadores) {
		ArrayList<Siete> ot = new ArrayList<Siete>();
		for (Siete siete : otrosJugadores) {
			if (!siete.equals(this)) {
				ot.add(siete);
			}
		}
		this.otrosJugadores = ot;
	}

	public void setOtrosJugadores(ArrayList<Siete> otrosJugadores) {
		for (Siete siete : otrosJugadores) {
			siete.setMisJugadores(otrosJugadores);
		}
	}

	public void addOtroJugador(Siete elOtroHilo) {
		otrosJugadores.add(elOtroHilo);
	}
}
