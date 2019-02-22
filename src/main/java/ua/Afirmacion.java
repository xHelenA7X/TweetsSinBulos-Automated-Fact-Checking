package ua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerWrapper;


@WebServlet(name="procesaafirmacion",urlPatterns={"/procesaafirmacion"})
public class Afirmacion extends HttpServlet{
	private static final Logger log = Logger.getLogger(Afirmacion.class.getName());
	  
	private void JSON(HttpServletResponse response, String texto) throws ServletException, IOException{
		 response.setContentType("application/json");
		 response.setCharacterEncoding("utf-8");
		 String output = "";
		 int error = HttpServletResponse.SC_OK;	 
		 if(texto != ""){
			 output = "{\"result\": [\""+texto+"\"]}"; 
		 }
		 else{
			 output = "{\"error\": [\"No hay texto asociado."+"\"]}";
		 }
		 
		 response.setStatus(error);
		 PrintWriter out = response.getWriter();
		 out.println(output);
	}
	
	private void anotaTexto(String[] arrayTexto, String idioma){
		TreeTaggerWrapper tt = new TreeTaggerWrapper<String>();
		try {
			
			switch(idioma){
			case "en":
				tt.setModel("english.par");
			default:
				tt.setModel("spanish.par");
			}
			
			tt.setHandler(new TokenHandler<String>() {
                public void token(String token, String pos, String lemma) {
                        log.warning(token + "\t" + pos + "\t" + lemma);
                }
			});
			
			tt.process(Arrays.asList(arrayTexto));
			
		} catch (Exception e) {
			log.warning("Excepcion: " + e.toString());
		}
		finally{
			tt.destroy();
		}
			
			
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException { 
	   String texto = req.getParameter("texto");
	   String idioma = req.getParameter("idioma");
	   if(texto != ""){
		   texto = texto.trim();
		   texto = texto.replace(",", "");
		   texto = texto.replace(".", "");
		   texto = texto.replace(":", "");
		   texto = texto.replace(";", "");
		   texto = texto.replace("#", "");
		   String []arrayTexto = texto.split(" ");
		   anotaTexto(arrayTexto,idioma);
	   }
	   JSON(resp,texto);
	}
}
