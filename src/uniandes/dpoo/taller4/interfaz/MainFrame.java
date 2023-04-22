package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.Tablero;

public class MainFrame extends LightsOutFrame {
	
	// COMPONENTS
	private SettingsMenu settingsMenu;
	private OptionsMenu optionsMenu;
	private GameBoard gameBoard;
	private InfoGameDisplay infoGameDisplay;
	
	// DATA
	private Tablero tablero;
	
	public MainFrame(Tablero tablero) {
		this.tablero = tablero;
		this.settingsMenu = new SettingsMenu();
		this.optionsMenu = new OptionsMenu(this);
		this.gameBoard = new GameBoard(this);
		this.infoGameDisplay = new InfoGameDisplay();
		
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
	
	public void restart() {
		this.tablero = new Tablero(this.settingsMenu.getBoardOption()[0]);
		this.gameBoard.refresh();
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
		this.infoGameDisplay.setNumberPlays(0);
	}
	
	public void refresh() {
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
		this.infoGameDisplay.setNumberPlays(this.tablero.darJugadas());
	}
	
	public Integer getLevel() {
		return this.settingsMenu.getLevelNumber();
	}
	
	public Boolean boardIsLock() {
		return !this.optionsMenu.getUnlockGame();
	}
	
	public Boolean isNewGame() {
		return this.optionsMenu.getGamer().isEmpty();
	}
	
	public Tablero getBoard() {
		return this.tablero;
	}
}
