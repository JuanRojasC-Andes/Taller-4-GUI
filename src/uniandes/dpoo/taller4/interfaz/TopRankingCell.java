package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uniandes.dpoo.taller4.modelo.RegistroTop10;

public class TopRankingCell implements ListCellRenderer<RegistroTop10> {

	@Override
	public Component getListCellRendererComponent(JList<? extends RegistroTop10> list, RegistroTop10 value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		// COMPONENTS
		GridLayout layout;
		JPanel panel;
		
		// HEADERS
		if (index == 0) {
			layout = new GridLayout(2, 3);
			panel = new JPanel(layout);
			panel.add(labelForHeader("Rank"));
			panel.add(labelForHeader("Jugador"));
			panel.add(labelForHeader("Puntaje"));
		} else {
			layout = new GridLayout(1, 3);
			panel = new JPanel(layout);
		}
		
		Color color = index < 3 ? Color.GREEN : Color.BLACK;
		JLabel rank = labelForRow(String.valueOf(index + 1), color);
		JLabel gamer = labelForRow(value.darNombre(), color);
		JLabel points = labelForRow(String.valueOf(value.darPuntos()), color);
		
		panel.add(rank);
		panel.add(gamer);
		panel.add(points);
		
		return panel;
	}
	
	private JLabel labelForHeader(String title) {
		JLabel header = new JLabel(title);
		header.setBackground(Constants.blueColor);
		header.setForeground(Color.WHITE);
		header.setOpaque(true);
		header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(new EmptyBorder(5, 10, 5, 10));
		return header;
	}
	
	private JLabel labelForRow(String value, Color color) {
		JLabel header = new JLabel(value);
		header.setBackground(Color.WHITE);
		header.setForeground(color);
		header.setOpaque(true);
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(new EmptyBorder(3, 3, 3, 3));
		return header;
	}
}
