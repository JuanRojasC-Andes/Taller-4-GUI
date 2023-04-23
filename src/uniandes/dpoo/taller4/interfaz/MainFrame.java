package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class MainFrame extends LightsOutFrame {
	
	// COMPONENTS
	private SettingsMenu settingsMenu;
	private OptionsMenu optionsMenu;
	private GameBoard gameBoard;
	private InfoGameDisplay infoGameDisplay;
	
	// DATA
	private Tablero tablero;
	private Top10 top10;
	private File top10File;
	private Map<String, String> gamersHistorial;
	
	public MainFrame(Tablero tablero, Top10 top10, File top10File) {
		this.gamersHistorial = new HashMap<String, String>(Map.of("past", "", "current", ""));
		this.top10File = top10File;
		this.tablero = tablero;
		this.top10 = top10;
		this.settingsMenu = new SettingsMenu();
		this.optionsMenu = new OptionsMenu(this);
		this.gameBoard = new GameBoard(this);
		this.infoGameDisplay = new InfoGameDisplay();
		
//		FlatLightLaf.install();
		
		frameComponents();
		frameSettings();
	}
	
	private void frameSettings() {
		// CUSTOMIZATION
		this.setSize(720, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("LightsOut");
		this.setResizable(false);
		
		// LISTENERS
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					top10.salvarRecords(top10File);
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					System.out.println("Fallo el guardado del top10");
					e1.printStackTrace();
				}
			}
		});
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
	
	private void cleanBoard() {
		this.tablero = new Tablero(this.settingsMenu.getBoardOption()[0]);
		this.gameBoard.refresh();
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
		this.infoGameDisplay.setNumberPlays(0);
	}
	
	private void newGamer() {
		this.gamersHistorial.put("past", this.gamersHistorial.get("current"));
		this.gamersHistorial.put("current", this.optionsMenu.getGamer());
		if (!this.gamersHistorial.get("past").isEmpty()) {
			this.top10.agregarRegistro(
					this.gamersHistorial.get("past"),
					this.tablero.calcularPuntaje()
			);	
		}
	}
	
	/**
	 * Reinicia el tablero y toda la informacion en pantalla, ademas guarda los datos del jugador y 
	 * su puntaje en el top.
	 * */
	public void start() {
		newGamer();
		cleanBoard();
	}
	
	/**
	 * Reinicia el tablero y toda la informacion en pantalla, no guarda la partida
	 * */
	public void restart() {
		cleanBoard();
	}
	
	/**
	 * Recarga y acutaliza la informacion en pantalla, en caso de que se cambie el jugador registra
	 * la partida en el top.
	 * */
	public void refresh() {
		if (!this.gamersHistorial.get("current").equals(this.optionsMenu.getGamer())) {
			newGamer();
			this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
		}
		this.infoGameDisplay.setNumberPlays(this.tablero.darJugadas());
	}
	
	/**
	 * Termina el juego, guardando los datos de la partida en el top sin limpiar el tablero.
	 * */
	public void finish() {
		newGamer();
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
	
	public Top10 getTop() {
		return this.top10;
	}
}
