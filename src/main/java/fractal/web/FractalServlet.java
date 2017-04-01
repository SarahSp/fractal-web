package fractal.web;

import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FractalServlet", urlPatterns = {"/fractal/*"})
public class FractalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final FractalRenderer renderer = new FractalRenderer();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("image/png");
		RenderedImage image = renderer.render(200, 100);
		ImageIO.write(image, "png", resp.getOutputStream());	
	}

}
