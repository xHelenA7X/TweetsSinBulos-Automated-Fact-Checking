package ua.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import ua.controller.TweetController;
import ua.util.Pair;

public class FreelingXML {	
	private static final Logger log = Logger.getLogger(FreelingXML.class.getName());
	
	public static void generaFicheroEntrada(String idTweet,String texto) throws IOException {
		String ruta = "/etc/tweets/"+idTweet+".txt";
		File archivo = new File(ruta);
		BufferedWriter bw;
		if(!archivo.exists()) {
		    // El fichero no existe todavia en el servidor
			bw = new BufferedWriter(new FileWriter(archivo));
			int longitud = texto.length();
			char ultimo = texto.charAt(longitud-1);
			if(ultimo != '.') {
				texto+=".";
			}
			bw.write(texto);
			bw.close();
		}
	}
	
	
	public static void generaFicheroSalida(String idTweet) {
        // Contiene la instruccion a ejecutar
        String comando = "analyzer_client localhost:5000 < /etc/tweets/"+idTweet+".txt";
        try
        {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(comando);
            // Inicializa el lector del buffer
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String resultado = "";
            String inputLine;
            // Bucle mientas reciba parametros del buffer
            while ((inputLine = in.readLine()) != null)
            {
            	if(!inputLine.contains("OUTPUT")) {
	            	// Si deseamos capturar el resultado para posteriormente
	                // utilizarlo en nuestra aplicacion
	                resultado += inputLine;
            	}
            }
            in.close();
            
	        //escribimos el resultado
	        String ruta = "/etc/tweets/"+idTweet+"-salida.xml";
			File archivo = new File(ruta);
			BufferedWriter bw;
			if(!archivo.exists()) {
			    // El fichero no existe todavia en el servidor
				bw = new BufferedWriter(new FileWriter(archivo));
				bw.write(resultado);
				bw.close();
			}
			
        } catch (IOException e) {
            System.out.println(e);
        }
	}
}
