package uniandes.dpoo.taller4.main;

import uniandes.dpoo.taller4.interfaz.MainFrame;
import uniandes.dpoo.taller4.modelo.Tablero;

public class LightsOutMain {
	
	// MAIN
	public static void main(String[] args) {
		Tablero tablero = new Tablero(7);
		MainFrame mainframe = new MainFrame(tablero);
	}

}
