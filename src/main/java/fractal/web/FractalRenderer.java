package fractal.web;

import java.awt.image.BufferedImage;

public class FractalRenderer {
	
	public BufferedImage render(int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		return image;
	}

}
