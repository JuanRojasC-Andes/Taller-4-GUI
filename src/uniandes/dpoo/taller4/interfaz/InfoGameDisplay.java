package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoGameDisplay extends JPanel {
	
	private JLabel labelTitleNumberPlays;
	private JLabel labelInfoNumberPlays;
	private JLabel labelTitlePlayer;
	private JLabel labelNamePlayer;
	
	public InfoGameDisplay() {
		this.labelTitleNumberPlays = new JLabel("Jugadas:");
		this.labelInfoNumberPlays = new JLabel("", SwingConstants.CENTER);
		this.labelTitlePlayer = new JLabel("Jugador:");
		this.labelNamePlayer = new JLabel("", SwingConstants.CENTER);
		configInfoGameDisplay();
	}
	
	private void configInfoGameDisplay() {
		// LAYOUT
		GridLayout layout = new GridLayout(1, 2);
		this.setLayout(layout);
		
		// CUSTOMIZATION
		this.labelTitleNumberPlays.setFont(new Font(getName(), Font.PLAIN, 17));
		this.labelTitlePlayer.setFont(new Font(getName(), Font.PLAIN, 17));
		this.labelInfoNumberPlays.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.labelInfoNumberPlays.setPreferredSize(new Dimension(70, 20));
		this.labelNamePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.labelNamePlayer.setPreferredSize(new Dimension(150, 20));
		
		// SUBLAYOUTS
		JPanel panelCol1 = new JPanel();
		panelCol1.setLayout(new FlowLayout());
		panelCol1.add(this.labelTitleNumberPlays);
		panelCol1.add(Box.createRigidArea(new Dimension(50, 0)));
		panelCol1.add(this.labelInfoNumberPlays);
		
		JPanel panelCol2 = new JPanel();
		panelCol2.setLayout(new FlowLayout());
		panelCol2.add(this.labelTitlePlayer);
		panelCol2.add(Box.createRigidArea(new Dimension(50, 0)));
		panelCol2.add(this.labelNamePlayer);
		
		
		// COMPONENTS
		this.add(panelCol1);
		this.add(panelCol2);
	}
}
