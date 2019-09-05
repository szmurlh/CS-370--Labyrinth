import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet (BufferedImage ss) {
		this.image = ss;
	}
	
	// This method will pick a sprite from the
	// spritesheet based on its x and y position.
	public BufferedImage getImage(int x, int y, int width, int height) {
		BufferedImage img = image.getSubimage(width*x, height*y, width-1, height-1);
		return img;
	}
	
}
