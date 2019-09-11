import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	// Simple class to load in an image from a file.
	public BufferedImage loadImage(String filename) throws IOException {
		
		image = ImageIO.read(new File(filename));
		return image;
		
	}
	
}
