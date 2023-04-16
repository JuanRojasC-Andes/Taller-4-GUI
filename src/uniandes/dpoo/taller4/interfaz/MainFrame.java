package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatLightLaf;

public class MainFrame extends JFrame {
	
	public static void main(String[] args) {
		MainFrame mainframe = new MainFrame();
	}
	
	private SettingsMenu settingsMenu;
	
	public MainFrame() {
		this.settingsMenu = new SettingsMenu();
		
//		FlatLightLaf.install();
		
		frameComponents();
		frameSettings();
	}
	
	private void frameSettings() {
		this.setSize(900, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void frameComponents() {
		// LAYOUT
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		// COMPONENTS
		this.add(settingsMenu, BorderLayout.NORTH);
	}
}
