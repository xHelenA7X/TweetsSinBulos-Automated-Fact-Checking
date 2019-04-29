package ua.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

import ua.controller.TweetController;
import ua.dao.FuentesFiablesDao;
import ua.model.FuenteExterna;

public class Googler {
	private static final Logger log = Logger.getLogger(Googler.class.getName());
	
	public static String BusquedaFuentesExternas(String fuente,String keywords_str) {
		String salida = "";
		try
        {
            Runtime r = Runtime.getRuntime();
            String comando = "googler -C -w "+ fuente + " " + keywords_str;
            Process p = r.exec(comando);
            // Inicializa el lector del buffer
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
 
            String inputLine;    
            int primerCaracter = in.read();
            
            if(primerCaracter != 103) { //Si no encontramos nada el primer caracter que saca siempre es 'g'
	            // Bucle mientas reciba parametros del buffer
	            for(int i = 0; i < 8 && (inputLine=in.readLine()) != null; i++) {
	            	if(in.ready()) {
		                salida += inputLine + "\n";
	            	}         
	            }
            }
            else {
            	salida = "Sin resultados para " + fuente + "\n";
            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }
		return salida;
	}
	
	public static FuenteExterna procesaNoticiaFuenteExterna(String noticia) {
		String[] partes = noticia.split("\n");
		FuenteExterna fuente = new FuenteExterna();
		for(int i = 0; i < partes.length; i++) {
			log.warning(partes[i]);
		}
		
		return fuente;
		
	}
	
	
}
