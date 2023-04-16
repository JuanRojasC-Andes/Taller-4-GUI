package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
		MainFrame mainframe = new MainFrame();
	}
	
	private SettingsMenu settingsMenu;
	private OptionsMenu optionsMenu;
	private GameBoard gameBoard;
	private InfoGameDisplay infoGameDisplay;
	
	private Integer[] boardOption;
	
	public MainFrame() {
		this.settingsMenu = new SettingsMenu();
		this.optionsMenu = new OptionsMenu(this);
		this.gameBoard = new GameBoard(settingsMenu.getBoardOption()[0], settingsMenu.getBoardOption()[1]);
		this.infoGameDisplay = new InfoGameDisplay();
		
//		FlatLightLaf.install();
		
		frameComponents();
		frameSettings();
	}
	
	private void frameSettings() {
		this.setSize(680, 550);
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
		this.gameBoard.refresh(settingsMenu.getBoardOption()[0], settingsMenu.getBoardOption()[1]);
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
	}
	
	public void refreshData() {
		this.infoGameDisplay.setPlayer(this.optionsMenu.getGamer());
	}
}
