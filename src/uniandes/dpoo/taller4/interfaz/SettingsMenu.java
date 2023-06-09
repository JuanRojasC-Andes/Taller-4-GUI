package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SettingsMenu extends JPanel {
	
	// COMPONENTS
	private JLabel labelSizeGrid;
	private JComboBox<String> boxSizeGrid;
	private JLabel labelLevel;
	private JRadioButton btnEasyLevel;
	private JRadioButton btnMiddleLevel;
	private JRadioButton btnHardLevel;
	private ButtonGroup btnLevelGroup;
	
	// DATA
	private ArrayList<String> optionsLevel;
	private Integer[] boardOption;
	private String level;
	
	public SettingsMenu() {
		this.optionsLevel = new ArrayList<String>(Arrays.asList("Facil", "Medio", "Dificil"));
		this.labelSizeGrid = new JLabel("Tama�o:");
		this.boxSizeGrid = new JComboBox<String>();
		this.labelLevel = new JLabel("Dificultad:");
		this.btnEasyLevel = new JRadioButton(this.optionsLevel.get(0));
		this.btnMiddleLevel = new JRadioButton(this.optionsLevel.get(1));
		this.btnHardLevel = new JRadioButton(this.optionsLevel.get(2));
		this.btnLevelGroup = new ButtonGroup();
		configSettingsMenu();
		configBoxSizeGrid();
		configLevelButtons();
	}
	
	// PANEL CONFIGURATION
	
	private void configSettingsMenu() {
		// LAYOUT
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		
		// CUSTOMIZATION
		this.setBackground(Constants.blueColor);
		this.labelSizeGrid.setForeground(Color.WHITE);
		this.labelLevel.setForeground(Color.WHITE);
		this.btnEasyLevel.setForeground(Color.WHITE);
		this.btnMiddleLevel.setForeground(Color.WHITE);
		this.btnHardLevel.setForeground(Color.WHITE);
		
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
		this.boxSizeGrid.addItem("3x3");
		this.boxSizeGrid.addItem("5x5");
		this.boxSizeGrid.addItem("7x7");
		
		this.boxSizeGrid.setSelectedIndex(2);
		this.boardOption = new Integer[]{7, 7};
		
		// CUSTOMIZATION
		this.boxSizeGrid.setForeground(Color.BLACK);
		this.boxSizeGrid.setBackground(Color.white);
		
		// LISTENERS
		this.boxSizeGrid.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
	            String selected = (String) boxSizeGrid.getSelectedItem();
	            Integer selectedWidth = Integer.parseInt(selected.split("x")[0]);
	            Integer selectedHeight = Integer.parseInt(selected.split("x")[1]);
	            boardOption = new Integer[]{selectedWidth, selectedHeight};
			}
		});
	}
	
	private void configLevelButtons() {
		this.btnLevelGroup.add(btnEasyLevel);
		this.btnLevelGroup.add(btnMiddleLevel);
		this.btnLevelGroup.add(btnHardLevel);
		this.btnEasyLevel.setSelected(true);
		this.level = this.btnEasyLevel.getText();
		
		Iterator<AbstractButton> iterator = this.btnLevelGroup.getElements().asIterator();
		while(iterator.hasNext()) {
			JRadioButton btn = (JRadioButton) iterator.next();
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JRadioButton b = (JRadioButton) e.getSource();
					level = b.getText();
				}
			});	
		}
	}

	public Integer[] getBoardOption() {
		return boardOption;
	}

	public String getLevel() {
		return level;
	}
	
	public Integer getLevelNumber() {
		return this.optionsLevel.indexOf(this.level) + 1;
	}
}
