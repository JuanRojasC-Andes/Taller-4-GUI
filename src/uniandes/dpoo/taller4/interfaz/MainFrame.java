package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatLightLaf;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
		MainFrame mainframe = new MainFrame();
	}
	
	private SettingsMenu settingsMenu;
	private OptionsMenu optionsMenu;
	
	public MainFrame() {
		this.settingsMenu = new SettingsMenu();
		this.optionsMenu = new OptionsMenu();
		
//		FlatLightLaf.install();
		
		frameComponents();
		frameSettings();
	}
	
	private void frameSettings() {
		this.setSize(700, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("LightsOut");
	}
	
	private void frameComponents() {
		// LAYOUT
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		// COMPONENTS
		this.add(settingsMenu, BorderLayout.NORTH);
		this.add(optionsMenu, BorderLayout.EAST);
	}
}
