package fractal.web;

import java.awt.image.BufferedImage;

public class FractalRenderer {
	
	private static final double DIVERGENCE_THRESHOLD = 2.0;
	
	private static final double THRESHOLD_SQR = DIVERGENCE_THRESHOLD * DIVERGENCE_THRESHOLD;
	
	public BufferedImage render(FractalOptions opt) {
		BufferedImage image = new BufferedImage(opt.imageWidth, opt.imageHeight, BufferedImage.TYPE_INT_RGB);
		IColorizer colorizer = DEFAULT_COLORIZER;
		
		for (int i = 0; i < opt.imageWidth; i++) {
			for (int j = 0; j < opt.imageHeight; j++) {
				double value = computeValue(i, j, opt);
				Pixel pixel = colorizer.color(value);
				image.setRGB(i, j, pixel.toRGB());
			}
		}
		
		return image;
	}
	
	 private double computeValue(double x, double y, FractalOptions opt) {
	        double cre = opt.centerRe + (x / opt.imageWidth - 0.5) * opt.widthRe;
	        double cim = opt.centerIm + (y / opt.imageHeight - 0.5) * opt.heightIm;
	        
	        double absolute;
	        int i = 0;
	        double re = opt.startRe;
	        double im = opt.startIm;
	        do {
	            double nextre = re * re - im * im + cre;
	            double nextim = 2 * re * im + cim;
	            re = nextre;
	            im = nextim;
	            i++;
	            absolute = re * re + im * im;
	        } while (absolute <= THRESHOLD_SQR && i < opt.iterationLimit);
	        
	        if (i < opt.iterationLimit) {
	            // the value is diverging
	            return i + THRESHOLD_SQR / absolute;
	        } else {
	            // the value is not diverging
	            return -absolute / THRESHOLD_SQR;
	        }
	    }
	 
	    /** the color used for non-diverging regions. */
	    private static final Pixel NON_DIVERGE_COLOR = new Pixel(96, 32, 32);
	    /** the number of iterations after which the color gradient repeats. */
	    private static final int COLOR_PERIOD = 64;
	    /** the default colorizer. */
	    private static final IColorizer DEFAULT_COLORIZER = x -> {
	        if (x < 0) {
	            // the pixel belongs to the non-diverging set
	            return NON_DIVERGE_COLOR;
	        } else {
	            // the pixel belongs to the diverging set
	            double remainder = (int) x % COLOR_PERIOD + x - Math.floor(x);
	            int shade;
	            if (remainder < COLOR_PERIOD / 2) {
	                shade = (int) Math.round(255 * remainder / (COLOR_PERIOD / 2));
	            } else {
	                shade = (int) Math.round(255 * (COLOR_PERIOD - remainder) / (COLOR_PERIOD / 2));
	            }
	            return new Pixel(shade, shade, 224);
	        }
	    };

}
