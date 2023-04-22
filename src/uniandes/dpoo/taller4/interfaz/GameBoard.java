package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uniandes.dpoo.taller4.modelo.Tablero;

public class GameBoard extends JPanel implements MouseListener {
	
	// MAIN FRAME
	private LightsOutFrame mainFrame;
	
	// CONSTANTS
	private final Integer widthCellMin = 90;
	private final Integer heightCellMin = 90;
	
	// CUSTOMIZATION VARIABLES
	private Integer widthCell = 90;
	private Integer heightCell = 90;
	private Color mainColor;
	private Color roundColor;
	
	// DATA
	private Integer size;
	private BufferedImage image;
	private Tablero tablero;
	private Integer level;
	
	public GameBoard(LightsOutFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.tablero = mainFrame.getBoard();
		this.level = mainFrame.getLevel();
		this.size = mainFrame.getBoard().darTablero().length;
		this.mainColor = Constants.yellow;
		this.roundColor = Color.BLACK;
		configGameBoard();
		loadImage();
		settingsBoard();
	}
	
	private void configGameBoard() {
		Integer width = this.size * this.widthCell;
		Integer height = this.size * this.heightCell;
		
		// CUSTOMIZATION
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
	}
	
	private void settingsBoard() {
		if (!this.mainFrame.isNewGame()) {
			this.tablero.desordenar(level);
		}
	}
	
	// LOAD IMAGE FOR ICON LIGHT
	private void loadImage() {
		try {
			image = ImageIO.read(new File("./data/luz.png"));
		} catch (Exception e) {
			System.out.println("Error cargando la imagen de la luz para le tablero");
			e.printStackTrace();
		}
	}
	
	// PAINT GAME BOARD
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		this.widthCell = (this.widthCellMin * 5) / this.size;
		this.heightCell = (this.heightCellMin * 5) / this.size;
		boolean[][] tablero = this.tablero.darTablero();
		
		for (int y = 0; y < tablero.length; y++) {
			boolean[] rowTablero = tablero[y];
			for (int x = 0; x < rowTablero.length; x++) {
				// BOARD CELL
				Boolean isLightOff = rowTablero[x];
				Boolean cellStatus = isLightOff && !this.mainFrame.isNewGame();
				Color backgroundColor = cellStatus? this.roundColor : this.mainColor;
				
				// RECTANGLE
				Integer separator = 4;
				Integer coordinateX = (x * this.widthCell) + (separator * x);
				Integer coordinateY = (y * this.heightCell) + (separator * y);
				
				// IMAGE
				Integer coordinateXImage = coordinateX +  ((this.widthCell - image.getWidth()) / 2);
				Integer coordinateYImage = coordinateY + ((this.heightCell - image.getHeight()) / 2);
				
				// PAINT
				paintCell(
						g,
						coordinateX, 
						coordinateY, 
						this.widthCell, 
						this.heightCell,
						!cellStatus,
						coordinateXImage, 
						coordinateYImage,
						backgroundColor
						
				);
			}
		}
	}
	
	public void paintCell(Graphics g, Integer x, Integer y, Integer width, Integer height, Boolean drawImage, Integer imageX, Integer imageY, Color backgroundColor) {
		Graphics2D g2d = (Graphics2D) g;
		
		// RECTANGLE
		g2d.setColor(backgroundColor);
		g2d.fillRoundRect(x, y, width, height, 10, 10);
		
		// IMAGE
		if(drawImage) {
			g2d.drawImage(image, null, imageX, imageY);	
		}
	}
	
	// LOCALIZE CLICK OF MOUSE
	private Integer[] convertirCoordenadasACasilla(Integer x, Integer y) {
		Integer ladoTablero = size; 
		Integer altoPanelTablero = getHeight(); 
		Integer anchoPanelTablero = getWidth();
		Integer altoCasilla = altoPanelTablero / ladoTablero; 
		Integer anchoCasilla = anchoPanelTablero / ladoTablero; 
		Integer fila = (Integer) (y / altoCasilla) - 1;
		Integer columna = (Integer) (x / anchoCasilla);
		return new Integer[] { fila, columna };
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!this.mainFrame.boardIsLock()) {
			Integer clickX = e.getX();
			Integer clickY = e.getY();
			Integer[] casilla = convertirCoordenadasACasilla(clickX, clickY);
			try {
				this.tablero.jugar(casilla[0], casilla[1]);
				paint(this.getGraphics());
				this.mainFrame.refresh();
			} catch (Exception ex) {
				System.out.println(ex.getLocalizedMessage());
			}	
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	// REFRESH
	public void refresh() {
		this.tablero = this.mainFrame.getBoard();
		this.size = this.mainFrame.getBoard().darTablero().length;
		settingsBoard();
		paint(this.getGraphics());
	}

}
