package uniandes.dpoo.taller4.interfaz;

import javax.swing.JFrame;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public abstract class LightsOutFrame extends JFrame {
	
	public abstract void start();
	
	public abstract void restart();
	
	public abstract void refresh();
	
	public abstract void finish();
	
	public abstract Integer getLevel();
	
	public abstract Boolean boardIsLock();
	
	public abstract Boolean isNewGame();
	
	public abstract Tablero getBoard();
	
	public abstract Top10 getTop();
}
