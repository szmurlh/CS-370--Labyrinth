import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	private double x;
	private double y;
	
	public int width = 126;
	public int height = 126;
	
	private BufferedImage tile;
	
	// Create a Tile instance and take it's image from
	// the spritesheet based on given x and y.
	public Tile (double x, double y, int number, Game game) {
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		tile = ss.getImage(number % 10, number / 10, width, height);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tile, (int)x, (int)y, null);
	}
	
	// Scales any tile by the number d
	public void scale(double d) {
		if (d == 1) return;
		if (d <= 0) return;
		if (d < 1) scaleDown(d);
		if (d > 1) scaleUp(d);
	}
	
	// Scales down the size of the BufferedImage
	// This is used in displaying the spare tile.
	public void scaleDown(double d) {
		if (d == 0) return;
		if (d >= 1) return;
		
		BufferedImage scaled = new BufferedImage((int)(d * width), (int)(d * height), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < scaled.getWidth(); i++) {
			for (int j = 0; j < scaled.getHeight(); j++) {
				int rgb = tile.getRGB((int)(i/d), (int)(j/d));
				scaled.setRGB(i, j, rgb);
			}
		}
		width = (int)(width*d);
		height = (int)(height*d);
		tile = scaled;
	}
	
	// Scales up the size of the BufferedImage
	// This is used in displaying the spare tile.
	public void scaleUp(double d) {
		if (d <= 1) return;
		
		BufferedImage scaled = new BufferedImage((int)(d*width)-1, (int)(d*height)-1, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < scaled.getWidth(); i++) {
			for (int j = 0; j < scaled.getHeight(); j++) {
				int rgb = tile.getRGB(Math.max(0, (int)(i/d)-1), Math.max(0, (int)(j/d)-1));
				scaled.setRGB(i, j, rgb);
			}
		}
		width = (int)(width * d);
		height = (int)(height * d);
		tile = scaled;
	}
	
	// This method will rotate the tile by the number of
	// turns specified. (eg. 1 -> 90 degrees, 2 -> 180 deg,
	// 3 -> 270 deg, etc.) All turns are right turns.
	public void rotate(int turns) {
		if (turns % 4 == 0) {
			return;
		}
		BufferedImage rotated = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width - 1; i++) {
			for (int j = 0; j < height - 1; j++) {
				if (turns % 4 == 1) {
					rotated.setRGB(width - 2 - j, i, tile.getRGB(i, j));
				} else if (turns % 4 == 2) {
					rotated.setRGB(width - 2 - i, height - 2 - j, tile.getRGB(i, j));
				} else {
					rotated.setRGB(j, height - 2 - i, tile.getRGB(i, j));
				}
			}
		}
		tile = rotated;
	}
	
	// setX and setY are used to randomize the movable tiles
	// and to change the places of the moving tiles.
	public void setX (double x) {
		this.x = x;
	}
	
	public void setY (double y) {
		this.y = y;
	}
	
}