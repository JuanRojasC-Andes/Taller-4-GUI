package uniandes.dpoo.taller4.interfaz;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameBoard extends JPanel implements MouseListener {
	
	private final Integer widthCell = 85;
	private final Integer heightCell = 85;
	
	private Integer cellsInX;
	private Integer cellsInY;
	private BufferedImage image;
	
	public GameBoard(Integer cellsInX, Integer cellsInY) {
		this.cellsInX = cellsInX;
		this.cellsInY = cellsInY;
		configGameBoard();
		loadImage();
	}
	
	private void configGameBoard() {
		Integer width = this.cellsInX * this.widthCell;
		Integer height = this.cellsInY * this.heightCell;
		
		// CUSTOMIZATION
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
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
		
		for (int y = 0; y < this.cellsInY; y++) {
			for (int x = 0; x < this.cellsInX; x++) {
				
				// RECTANGLE
				Integer separator = 2;
				Integer coordinateX = (x * this.widthCell) + (separator * x);
				Integer coordinateY = (y * this.heightCell) + (separator * y);
				g2d.setColor(Constants.yellow);
				g2d.fillRoundRect(coordinateX, coordinateY, widthCell, heightCell, 10, 10);
				
				// IMAGE
				Integer coordinateXImage = coordinateX +  ((this.widthCell - image.getWidth()) / 2);
				Integer coordinateYImage = coordinateY + ((this.heightCell - image.getHeight()) / 2);
				g2d.drawImage(image, null, coordinateXImage, coordinateYImage);
			}	
		}
	}
	
	// LOCALIZE CLICK OF MOUSE
	@Override
	public void mousePressed(MouseEvent e) {
		Integer clickX = e.getX();
		Integer clickY = e.getY();
		Integer[] casilla = convertirCoordenadasACasilla(clickX, clickY);
//		cantidades[casilla[0]][casilla[1]]++; principal.jugar(casilla[0], casilla[1]);
//		this.ultima_fila = casilla[0];
//		this.ultima_columna = casilla[1];
//		repaint();
	}
	
	private Integer[] convertirCoordenadasACasilla(Integer x, Integer y) {
		Integer ladoTablero = cellsInX; 
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
