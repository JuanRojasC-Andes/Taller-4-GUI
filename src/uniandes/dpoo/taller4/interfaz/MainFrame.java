package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.Tablero;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
		Tablero tablero = new Tablero(7);
		MainFrame mainframe = new MainFrame(tablero);
	}
	
	
	// COMPONENTS
	private SettingsMenu settingsMenu;
	private OptionsMenu optionsMenu;
	private GameBoard gameBoard;
	private InfoGameDisplay infoGameDisplay;
	
	// DATA
	private Tablero tablero;
	
	public MainFrame(Tablero tablero) {
		this.settingsMenu = new SettingsMenu();
		this.optionsMenu = new OptionsMenu(this);
		this.gameBoard = new GameBoard(tablero);
		this.infoGameDisplay = new InfoGameDisplay();
		this.tablero = tablero;
		
//		FlatLightLaf.install();
		
		frameComponents();
		frameSettings();
	}
	
	private void frameSettings() {
		this.setSize(720, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("LightsOut");
		this.setResizable(false);
	}
	
	private void frameComponents() {
		// LAYOUT
		BorderLayout layout = new BorderLayout(10, 10);
		this.setLayout(layout);
		
		// COMPONENTS
		this.add(settingsMenu, BorderLayout.NORTH);
		this.add(optionsMenu, BorderLayout.EAST);
		this.add(gameBoard, BorderLayout.CENTER);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(infoGameDisplay, BorderLayout.SOUTH);
		
		// LISTENERS
		this.addMouseListener(gameBoard);
	}
	
	public void refreshAll() {
		this.tablero = new Tablero(this.settingsMenu.getBoardOption()[0]);
		this.gameBoard.refresh(this.tablero);
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
	}
	
	public void refreshData() {
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
	}
}
