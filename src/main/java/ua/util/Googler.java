package ua.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.model.NoticiaFuenteExterna;

public class Googler {
	
	public static String BusquedaFuentesExternas(String fuente,String keywords_str) {
		String salida = "";
		try
        {
            Runtime r = Runtime.getRuntime();
            String comando = "googler -C --np -n 2 -w "+ fuente + " " + keywords_str;
            Process p = r.exec(comando);
            // Inicializa el lector del buffer
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine = "";
            
            
            while((inputLine=in.readLine()) != null){
                // Si deseamos capturar el resultado para posteriormente
                // utilizarlo en nuestra aplicacion
                salida += inputLine + "\n";
               // System.out.println(in.read());
                
            }
            if(salida.isEmpty()){
            	System.out.println("Sin resultados");
            	salida = "Sin resultados para " + fuente + "\n";
            }
            
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }
		return salida;
	}
	
	public static NoticiaFuenteExterna procesaNoticiaFuenteExterna(String noticia,String fuente) {
		String[] partes = noticia.split("\n");
		String link[] = new String[2];
		String titulo[] = new String[2];
		String cuerpo[] = new String[2];
		
		if(partes.length == 7) {
			link[0] = partes[1].replaceAll(" ", "");
			link[1] = partes[5].replaceAll(" ", "");
			
			titulo[0] = partes[0];
			titulo[1] = partes[4];
			
			cuerpo[0] = partes[2];
			cuerpo[1] = partes[6];
		}
		
		if(partes.length == 4) {
			link[0] = partes[1].replaceAll(" ", "");
			titulo[0] = partes[0];
			cuerpo[0] = partes[2];
		}
		
		if(partes.length == 1) {
			titulo[0] = partes[0];
		}
		
		NoticiaFuenteExterna fuenteObject = new NoticiaFuenteExterna(fuente,titulo,link,cuerpo);
		
		return fuenteObject;
	}
	
	
}
