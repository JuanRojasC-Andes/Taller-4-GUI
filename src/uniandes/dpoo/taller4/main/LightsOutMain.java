package uniandes.dpoo.taller4.main;

import java.io.File;

import uniandes.dpoo.taller4.interfaz.MainFrame;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class LightsOutMain {
	
	// MAIN
	public static void main(String[] args) {
		File top10File = new File("./data/top10.csv");	
		Top10 top10 = new Top10();
		top10.cargarRecords(top10File);
		Tablero tablero = new Tablero(7);
		MainFrame mainframe = new MainFrame(tablero, top10, top10File);
	}

}
