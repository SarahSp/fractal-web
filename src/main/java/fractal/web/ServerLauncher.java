package fractal.web;

import java.net.InetSocketAddress;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

public class ServerLauncher {
	
	public static void main(String[] args) {
		Server server = new Server(new InetSocketAddress("localhost", 8080));
		HandlerList handlerList = new HandlerList();
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase("src/main/webapp");
		resourceHandler.setWelcomeFiles(new String[] {"index.html"});
		handlerList.addHandler(resourceHandler);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setConfigurations(new Configuration[] {
			new AnnotationConfiguration(),
			new WebInfConfiguration()
		});
		webAppContext.setAttribute(WebInfConfiguration.CONTAINER_JAR_PATTERN, ".*fractal.*");
		handlerList.addHandler(webAppContext);
		server.setHandler(handlerList);
		Slf4jLog log = new Slf4jLog(ServerLauncher.class.getName());
		try {
			server.start();
			server.join();
		} catch (Exception exception) {
			log.warn(exception.getMessage());
			System.exit(1);
		}
	}

}
