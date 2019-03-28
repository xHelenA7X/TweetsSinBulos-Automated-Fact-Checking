package ua.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.dao.AdministradorDao;
import ua.model.Administrador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;



import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name="login",urlPatterns={"/login"})
public class LoginController extends HttpServlet {

  private static final Logger log = Logger.getLogger(LoginController.class.getName());
  private AdministradorDao dao;
  	
  public LoginController() {
      super();
      dao = new AdministradorDao();
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  String accion = req.getParameter("action");
	  if(accion.equals("logout")) {
			HttpSession session=req.getSession();  
	        session.invalidate();
	        Cookie ck=new Cookie("email","");  
	        ck.setMaxAge(0);  
	        resp.addCookie(ck);
	        String mensaje = "Sesión cerrada correctamente.";
	        RequestDispatcher view = req.getRequestDispatcher("login.jsp");
	        req.setAttribute("mensaje", mensaje);
		    view.forward(req, resp);
		}
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String dni = req.getParameter("dni");
	String contrasenya = req.getParameter("psw");
	String mensaje = "";
	Administrador admin = dao.getAdministradorByDni(dni);
	boolean error=false;
	
	if(admin.getContrasenya() != null) {
		if(admin.getContrasenya().equals(dao.MD5(contrasenya))) {
			HttpSession session=req.getSession();  //Variable de sesion del usuario
			Cookie ck=new Cookie("dni",admin.getDni()); //cookies 
            resp.addCookie(ck); 
            RequestDispatcher view = req.getRequestDispatcher("index.jsp");
            
		    session.setAttribute("nombre", admin.getNombre());
		    session.setAttribute("apellidos", admin.getApellidos());
		    session.setAttribute("dni", dni);
		    view.forward(req, resp);
		}
		else{
			mensaje = "ERROR: Contraseña incorrecta.";
			error = true;
		}
	}
	else {
		mensaje = "ERROR: El DNI indicado no es correcto o no está registrado.";
		error = true;
	}
	if(error) {
		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
	    req.setAttribute("mensaje", mensaje);
	    view.forward(req, resp);
	}
	
  }

}
