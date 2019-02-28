package ua.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="salida",urlPatterns={"/salida"})
public class SalidaController extends HttpServlet{
	private static final Logger log = Logger.getLogger(SalidaController.class.getName());
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	              throws IOException, ServletException { 
		RequestDispatcher view = req.getRequestDispatcher("/salida.jsp");
        view.forward(req, resp);
	}

}
