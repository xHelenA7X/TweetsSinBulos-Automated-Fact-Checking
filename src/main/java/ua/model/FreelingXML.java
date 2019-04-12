package ua.model;

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
	private List<String> NC; //Nombres Comunes
	private List<String> NP; //Nombres Propios;
	private List<String> Adv; //Adverbios
	List<Pair<String, String>> Verbos; //Vector de pares ya que almacenare verbo conjugado y su infinitivo
	private List<String> AdvAfirmativos;
	private List<String> AdvNegativos;
	private static final Logger log = Logger.getLogger(FreelingXML.class.getName());
	
	public FreelingXML() {	
		NC = new ArrayList<String>();
		NP = new ArrayList<String>();
		Adv = new ArrayList<String>();
		Verbos = new ArrayList<Pair<String, String>>();
		AdvAfirmativos = new ArrayList<String>();
		AdvNegativos = new ArrayList<String>();
	}
	
	public void generaFicheroEntrada(String idTweet,String texto) throws IOException {
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
	
	
	public void generaFicheroSalida(String idTweet) {
        // Contiene la instruccion a ejecutar
        String comando = "analyzer_client localhost:5000 < /etc/tweets/1102636412334149634.txt > /etc/tweets/1102636412334149634-salida.xml";
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
	
	public void extraeEtiquetas(String archivoXML) {
		File archivo = new File("/etc/tweets/"+archivoXML);
		SAXBuilder constructorSAX = new SAXBuilder();
		
		try {
		    Document documento = (Document)constructorSAX.build(archivo);
		    Element nodoRaiz = documento.getRootElement();
		    /* Obtenemos la lista de los nodos con la etiqueta
		     * "token" que se encuentran en el nodo raiz */
		    List listaAplicaciones = nodoRaiz.getChildren("token");
		    /* Recorremos esta lista imprimiendo los elementos
		     * dentro de cada aplicacion y su categoría */
		    for (int i=0; i<listaAplicaciones.size(); i++) {

		        /* Obtenemos el elemento de la lista */
		        Element nodo = (Element)listaAplicaciones.get(i);

		        /* Obtenemos el atributo "id" del nodo */
		        String id = nodo.getAttribute("id").getValue();

		        /* Obtenemos los datos almacenados en los subnodos
		         * "nombre" y "categoria" de cada nodo "aplicacion" */
		        String form = nodo.getAttribute("form").getValue();
		        String lemma = nodo.getAttribute("lemma").getValue();
		        String ctag = nodo.getAttribute("ctag").getValue();
		        String type = nodo.getAttribute("type").getValue();

		        
		        switch(ctag) {
			        case "NC":
			        	NC.add(form);
			        	break;
			        case "NP":
			        	NP.add(form);
			        	break;   
		        }

		    }
		} catch (JDOMException e) {
		    System.out.println("Fichero no valido");
		    e.printStackTrace();
		} catch (IOException e) {
		    System.out.println("Fichero no valido");
		    e.printStackTrace();
		}
	}
	
	public List<String> getNC() {
		return NC;
	}
	public void setNCG(List<String> nCG) {
		NC = nCG;
	}
	public List<String> getNP() {
		return NP;
	}
	public void setNCP(List<String> nCP) {
		NP = nCP;
	}
	public List<String> getAdv() {
		return Adv;
	}
	public void setAdv(List<String> adv) {
		Adv = adv;
	}
	public List<Pair<String,String>> getVerbos() {
		return Verbos;
	}
	public void setVerbos(List<Pair<String, String>> verbos) {
		Verbos = verbos;
	}
	public List<String> getAdvAfirmativos() {
		return AdvAfirmativos;
	}
	public void setAdvAfirmativos(List<String> advAfirmativos) {
		AdvAfirmativos = advAfirmativos;
	}
	public List<String> getAdvNegativos() {
		return AdvNegativos;
	}
	public void setAdvNegativos(List<String> advNegativos) {
		AdvNegativos = advNegativos;
	}

	@Override
	public String toString() {
		return "FreelingXML [NCG=" + NC + ", NCP=" + NP + ", Adv=" + Adv + ", Verbos=" + Verbos + ", AdvAfirmativos="
				+ AdvAfirmativos + ", AdvNegativos=" + AdvNegativos + "]";
	}
	
	
}
