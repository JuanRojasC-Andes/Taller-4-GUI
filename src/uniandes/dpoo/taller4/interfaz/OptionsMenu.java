package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class OptionsMenu extends JPanel {
	
	private MainFrame mainFrame;
	
	private JButton btnNewGame;
	private JButton btnRestartGame;
	private JButton btnTop10;
	private JButton btnChangeGamer;
	
	public OptionsMenu(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.btnNewGame = new JButton("NUEVO");
		this.btnRestartGame = new JButton("REINICIAR");
		this.btnTop10 = new JButton("TOP-10");
		this.btnChangeGamer = new JButton("CAMBIAR JUGADOR");
		configOptionsMenu();
		configBtnNewGame();
		configBtnRestartGame();
		configBtnTop10();
		configBtnChangeGamer();
	}
	
	// PANEL CONFIGURATION
	private void configOptionsMenu() {
		// LAYOUT
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(8, 10, 8, 10);
		
		// CUSTOMIZATION
		this.setFont(new Font(getName(), Font.PLAIN, 60));
		
		// ADD COMPONENTS
		this.add(btnNewGame, constraints);
		this.add(btnRestartGame, constraints);
		this.add(btnTop10, constraints);
		this.add(btnChangeGamer, constraints);
	}
	
	// DEFAULT BUTTON CONFIGURATION
	private void configBtnDefault(JButton button) {
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		button.setBackground(Constants.blueColor);
		button.setForeground(Color.WHITE);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setPreferredSize(new Dimension(200, 30));
		button.setBorder(new LineBorder(Color.GRAY, 1));
	}
	
	// BUTTONS CONFIGURATIONS
	private void configBtnNewGame() {
		// CUSTOMIZATION
		configBtnDefault(this.btnNewGame);
		
		// LISTENER
		this.btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("boton nuevo juego oprimido");
			}
		});
	}
	
	private void configBtnRestartGame() {
		// CUSTOMIZATION
		configBtnDefault(this.btnRestartGame);
		
		// LISTENER
		this.btnRestartGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.refresh();
			}
		});
	}
	
	private void configBtnTop10() {
		// CUSTOMIZATION
		configBtnDefault(btnTop10);
		
		// LISTENER
		this.btnTop10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("boton top 10 oprimido");
			}
		});
	}
	
	private void configBtnChangeGamer() {
		// CUSTOMIZATION
		configBtnDefault(btnChangeGamer);
		
		// LISTENER
		this.btnChangeGamer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("boton cambiar jugador oprimido");
			}
		});
	}

}
