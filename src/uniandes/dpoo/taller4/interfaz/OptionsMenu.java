package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class OptionsMenu extends JPanel {
	
	// MAIN FRAME
	private LightsOutFrame mainFrame;
	
	// COMPONENTS
	private JButton btnNewGame;
	private JButton btnRestartGame;
	private JButton btnTop10;
	private JButton btnChangeGamer;
	
	// DATA
	private String gamer;
	private Boolean unlockGame;
	
	public OptionsMenu(LightsOutFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.btnNewGame = new JButton("NUEVO");
		this.btnRestartGame = new JButton("REINICIAR");
		this.btnTop10 = new JButton("TOP-10");
		this.btnChangeGamer = new JButton("CAMBIAR JUGADOR");
		this.gamer = "";
		this.unlockGame = false;
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
				unlockGame = true;
				dialogForNameUser("Nuevo Juego", "Nombre Jugador", "Iniciar", mainFrame::start);
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
				mainFrame.restart();
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
				dialogForNameUser("Cambiar Jugador", "Nombre Jugador", "Cambiar", mainFrame::refresh);
			}
		});
	}

	private void dialogForNameUser(String titleDialog, String titleLabel, String buttonText, Runnable action) {
		// DIALOG
		JDialog dialog = new JDialog(mainFrame, titleDialog, ModalityType.APPLICATION_MODAL);
		dialog.setSize(350, 200);
		dialog.setLocationRelativeTo(mainFrame);
		
		// LAYOUT
		dialog.setLayout(new BorderLayout());
		
		// COMPONENTS
		JLabel title = new JLabel(titleLabel);
		title.setBackground(Constants.blueColor);
		title.setForeground(Color.WHITE);
		title.setOpaque(true);
		title.setFont(new Font(getName(), Font.PLAIN, 14));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBorder(new EmptyBorder(5, 10, 5, 10));
		dialog.add(title, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
		
		JTextField inputNickName = new JTextField();
		inputNickName.setBorder(new EmptyBorder(5, 5, 5, 5));
		inputNickName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(inputNickName);
		
		centerPanel.add(Box.createRigidArea(new Dimension(200, 20)));
		
		JButton btnAccept = new JButton(buttonText);
		configBtnDefault(btnAccept);
		btnAccept.setBorder(new EmptyBorder(10, 40, 10 ,40));
		btnAccept.setAlignmentX(CENTER_ALIGNMENT);
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamer = inputNickName.getText();
				dialog.dispose();
				action.run();
			}
		});
		centerPanel.add(btnAccept);
		
		dialog.add(centerPanel, BorderLayout.CENTER);
		
		dialog.setVisible(true);
	}
	
	public String getGamer() {
		return gamer;
	}
	
	public Boolean getUnlockGame() {
		return unlockGame;
	}
}
