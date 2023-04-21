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
	
	private final Integer widthCellMin = 90;
	private final Integer heightCellMin = 90;
	
	private Integer widthCell = 90;
	private Integer heightCell = 90;
	private Color mainColor;
	private Color roundColor;
	
	private Integer size;
	private BufferedImage image;
	private Tablero tablero;
	private Integer level;
	private Runnable eventListener;
	
	private List<List<GameCell>> board;
	
	public GameBoard(Tablero tablero, Integer level) {
		this.tablero = tablero;
		this.level = level;
		this.size = tablero.darTablero().length;
		this.board = new ArrayList<>();
		this.mainColor = Constants.yellow;
		this.roundColor = Color.BLACK;
		configGameBoard();
		settingsBoard();
		loadImage();
	}
	
	public GameBoard(Tablero tablero, Integer level, Runnable eventListener) {
		this.tablero = tablero;
		this.level = level;
		this.size = tablero.darTablero().length;
		this.board = new ArrayList<>();
		this.mainColor = Constants.yellow;
		this.roundColor = Color.BLACK;
		this.eventListener = eventListener;
		configGameBoard();
		settingsBoard();
		loadImage();
	}
	
	private void configGameBoard() {
		Integer width = this.size * this.widthCell;
		Integer height = this.size * this.heightCell;
		
		// CUSTOMIZATION
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
	}
	
	private void settingsBoard() {
		this.tablero.desordenar(level);
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
			List<GameCell> row = new ArrayList<>();
			for (int x = 0; x < rowTablero.length; x++) {
				// BOARD CELL
				Boolean isOnOrOff = rowTablero[x];
				Color backgroundColor = isOnOrOff? Color.BLACK : Constants.yellow;
				
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
						!isOnOrOff,
						coordinateXImage, 
						coordinateYImage,
						backgroundColor
						
				);
			}
			this.board.add(row);
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
		Integer clickX = e.getX();
		Integer clickY = e.getY();
		Integer[] casilla = convertirCoordenadasACasilla(clickX, clickY);
		try {
//			GameCell gameCell = this.board.get(casilla[0]).get(casilla[1]).select();
//			paintCell(gameCell, this.getGraphics());
			this.tablero.jugar(casilla[0], casilla[1]);
			paint(this.getGraphics());
			if (eventListener != null) {
				eventListener.run();;
			}
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
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
	public void refresh(Tablero tablero, Integer level) {
		this.tablero = tablero;
		this.level = level;
		this.size = tablero.darTablero().length;
		settingsBoard();
		paint(this.getGraphics());
	}

}
