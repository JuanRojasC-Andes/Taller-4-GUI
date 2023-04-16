package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SettingsMenu extends JPanel {
	
	private JLabel labelSizeGrid;
	private JComboBox<String> boxSizeGrid;
	private JLabel labelLevel;
	private JRadioButton btnEasyLevel;
	private JRadioButton btnMiddleLevel;
	private JRadioButton btnHardLevel;
	private ButtonGroup btnLevelGroup;
	
	public SettingsMenu() {
		this.labelSizeGrid = new JLabel("Tamaño:");
		this.boxSizeGrid = new JComboBox<String>();
		this.labelLevel = new JLabel("Dificultad:");
		this.btnEasyLevel = new JRadioButton("Facil");
		this.btnMiddleLevel = new JRadioButton("Medio");
		this.btnHardLevel = new JRadioButton("Dificil");
		this.btnLevelGroup = new ButtonGroup();
		configSettingsMenu();
		configBoxSizeGrid();
		configLevelButtons();
	}
	
	private void configSettingsMenu() {
		// LAYOUT
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		
		// CUSTOMIZATION
		this.setBackground(Constants.blueColor);
		
		// ADD COMPONENTS
		this.add(labelSizeGrid);
		this.add(boxSizeGrid);
		this.add(labelLevel);
		this.add(btnEasyLevel);
		this.add(btnMiddleLevel);
		this.add(btnHardLevel);
	}
	
	private void configBoxSizeGrid() {
		// ITEMS
		this.boxSizeGrid.addItem("2x2");
		this.boxSizeGrid.addItem("3x3");
		this.boxSizeGrid.addItem("4x4");
		this.boxSizeGrid.addItem("5x5");
		
		// CUSTOMIZATION
		this.boxSizeGrid.setForeground(Color.BLACK);
		this.boxSizeGrid.setBackground(Color.white);
		
		// LISTENERS
		this.boxSizeGrid.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
	            String selected = (String) boxSizeGrid.getSelectedItem();
			}
		});
	}
	
	private void configLevelButtons() {
		this.btnLevelGroup.add(btnEasyLevel);
		this.btnLevelGroup.add(btnMiddleLevel);
		this.btnLevelGroup.add(btnHardLevel);
	}

}
