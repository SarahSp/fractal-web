package fractal.web;

/**
 * Color information for one screen pixel.
 */
public class Pixel {
	
	/**
	 * Cast an unsigned byte to integer.
	 */
	private static int toInt(byte b) {
		if (b < 0) {
			return ((int) b) + 256;
		} else {
			return (int) b;
		}
	}
	
	private byte red;
	private byte green;
	private byte blue;
		
	/**
	 * Create a pixel with given color values.
	 */
	public Pixel(byte red, byte green, byte blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	/**
	 * Create a pixel with given color values.
	 */
	public Pixel(int red, int green, int blue) {
		this.red = (byte) red;
		this.green = (byte) green;
		this.blue = (byte) blue;
	}

    public int toRGB() {
        return (toInt(red) << 16) | (toInt(green) << 8) | toInt(blue);
    }
    	
}
