package uniandes.dpoo.taller4.interfaz;

import javax.swing.JFrame;

import uniandes.dpoo.taller4.modelo.Tablero;

public abstract class LightsOutFrame extends JFrame {
	
	public abstract void restart();
	
	public abstract void refresh();
	
	public abstract Integer getLevel();
	
	public abstract Boolean boardIsLock();
	
	public abstract Boolean isNewGame();
	
	public abstract Tablero getBoard();
}
