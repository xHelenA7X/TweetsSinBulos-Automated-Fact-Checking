package ua.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import ua.dao.FuentesFiablesDao;

public class FuentesFiables {
	private String titulo;
	private String enlace;
	private String cuerpo;
	
	public FuentesFiables() {
		
	}
	
	public FuentesFiables(String dominio) {

	}
	
	public static String Busqueda(String fuente,String keywords_str) {
		String salida = "";
		try
        {
            Runtime r = Runtime.getRuntime();
            String comando = "googler -C -w "+ fuente + " " + keywords_str;
            Process p = r.exec(comando);
            // Inicializa el lector del buffer
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
 
            String inputLine;    
        	int i = 0;
            while ((inputLine = in.readLine()) != null)
            {
                // Si deseamos capturar el resultado para posteriormente
                // utilizarlo en nuestra aplicacion
                salida += inputLine;
                if(i == 7) {
                	break;
                }
                i++;
            }
            
            // Inicializa el lector del buffer
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
		return salida;
	}
	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
}
