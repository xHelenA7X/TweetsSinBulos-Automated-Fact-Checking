package ua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/* Algunas versiones del plugin de Jetty para Maven fallan si no
   hay al menos una clase en el directorio java. Creamos esta clase
   vacía para evitarlo. */
@WebServlet(name="generaInforme",urlPatterns={"/generainforme"})
public class MainServlet extends HttpServlet{
	private static final Logger log = Logger.getLogger(MainServlet.class.getName());
	
	@Override
	  public void doGet(HttpServletRequest req, HttpServletResponse resp)
	              throws IOException, ServletException { 
	    log.warning("Hola mundo");
	    String texto = req.getParameter("texto");
	  }
}
