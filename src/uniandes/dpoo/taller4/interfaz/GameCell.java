package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;

public class GameCell {
	
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Integer xImage;
	private Integer yimage;
	private Integer clicked;
	private Color currentColor;
	private Color roundColor;
	
	public GameCell(Integer x, Integer y, Integer width, Integer height, Integer xImage, Integer yimage,
			Color currentColor, Color roundColor) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xImage = xImage;
		this.yimage = yimage;
		this.currentColor = currentColor;
		this.roundColor = roundColor;
		this.clicked = 0;
	}
	
	public GameCell select() {
		this.invertColor();
		this.sumClick();
		return this;
	}
	
	public void invertColor() {
		Color current = this.currentColor;
		this.currentColor = this.roundColor;
		this.roundColor = current;
	}
	
	public void sumClick() {
		this.clicked += 1;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getxImage() {
		return xImage;
	}

	public void setxImage(Integer xImage) {
		this.xImage = xImage;
	}

	public Integer getYimage() {
		return yimage;
	}

	public void setYimage(Integer yimage) {
		this.yimage = yimage;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public Color getRoundColor() {
		return roundColor;
	}

	public void setRoundColor(Color roundColor) {
		this.roundColor = roundColor;
	}

	public Integer getClicked() {
		return clicked;
	}

	public void setClicked(Integer clicked) {
		this.clicked = clicked;
	}
}
