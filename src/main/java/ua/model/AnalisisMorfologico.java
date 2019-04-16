package ua.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 
 * @author helena
 ****** CLASE ANALISIS MORFOLOGICO ******
 *Se encarga de analizar morfologicamente el archivo xml que nos ha extraido ya etiquetado la herramienta Freeling
 *A continuación se describe cada elemento
 *String frase: Es la afirmacion que vamos a analizar.
 *List<Element> tokens Es la lista donde se almacenan los distintos elementos del xml, cada token del archivo
 *.xml es un elemento de esta lista
 *String firmeza: Entiendase por firmeza la posicion que tiene el sujeto ante una afirmacion, puede
 *ser negativa o positiva.
 *Ejemplo: Las personas que beben vino viven más años: positiva
 *Ejemplo: Las personas que beben vivo no viven más años: negativa
 *String conclusion: Conclusion que extraemos de la afirmacion, siguiendo el ejemplo anterior:
 *Conclusion 1: Se afirma que las personas que beben vivo viven más años.
 *Conclusion 2: Se niega que las personas que beben vivo viven más años.
 *Con la conclusion conozco que se esta diciendo como verdadero y que se esta diciendo como falso.
 *Se afirma que + conclusion
 *Se niega que + conclusion (se quitan los adverbios negativos)
 *List<String> adverbiosAquitar, si estamos ante una frase que niega, en la conclusion tendremos que quitar
 *el adverbio negativo, por tanto almaceno esos adverbios para luego en el 'Se niega que' sean eliminados
 *String rutaArchivoXML: Ruta en la que se encuentra el xml etiquetado por el servidor Freeling
 *int esSubordinada: -1 si NO es una frase subordinada, si la es, posicion en la que se haya el separador
 *Ejemplo: Fuentes desmienten que las personas que beben vino viven más años.
 *esSubordinada = 2
 *List<Integer> posAdverbios es una lista con la posicion que ocupan en la lista tokens los adverbios
 *List<Integer> posVerbos es una lista con la posicion que ocupan en la lista tokens los verbos
 *int tipoFrase: Esto depende de si la frase es subordinada o no.
 *Si no es subordinada:
 *Si la firmeza es afirmativa es tipo 1, si se niega es tipo 2.
 *Si es subordinada, distinguimos 4 tipos (ver documentacion readme.md)
 */

public class AnalisisMorfologico {
	private List<String> adverbiosAquitar;
	private List<Element> tokens;
	private List<Integer> posAdverbios;
	private List<Integer> posVerbos;
	private int esSubordinada;
	private int tipoFrase;
	private String frase;
	private String firmeza;
	private String conclusion;
	private String rutaArchivoXML;
	
	AnalisisMorfologico(String rutaArchivoXML){
		frase = "";
		conclusion = "";
		firmeza = "";
		this.rutaArchivoXML = rutaArchivoXML;
		tokens = new ArrayList<Element>();
		this.extraeTokens();
		this.extraeFrase();
		posAdverbios = new ArrayList<Integer>();
		posVerbos = new ArrayList<Integer>();
		adverbiosAquitar = new ArrayList<String>();
		this.esFraseSubordinada();
		tipoFrase=-1;
	}
	
	//Entiendase firmeza como la posicion que tiene el sujeto ante una afirmacion
	private String analisis() {
		String posicionAfirmacionAdverbio = "afirma";
		String posicionAfirmacionVerbo = "afirma";
		String firmeza = "afirma";
		if(posVerbos.size() > 0) {
			int posicion = posVerbos.get(0); //Aqui tendriamos que obtener el verbo y analizarlo
    		Element verbo = (Element) tokens.get(posVerbos.get(0));
    		String vString = verbo.getAttributeValue("lemma"); //Verbo en infinitivo
    		//Busqueda en la bd, si esta, lo que saque, si no, por defecto decimos que afirma
    		//if(...=="niega"){
    	}
    	if(posAdverbios.size() > 0) {
    		Element adverbio = null;
    		for(int j = 0; j < posAdverbios.size(); j++) {	
    			adverbio = (Element) tokens.get(posAdverbios.get(j));
				if(adverbio.getAttributeValue("type").equals("negative")) {
					posicionAfirmacionAdverbio = "niega";
					adverbiosAquitar.add(adverbio.getAttributeValue("lemma").toLowerCase());
				}
				else {
    				//Es un adverbio general, necesitamos respaldo de la bd
					//Consulta a la bd

    			}
   			}
    	}
    	if(posicionAfirmacionAdverbio == "niega" && posicionAfirmacionVerbo == "afirma") {
    		firmeza = "niega";
    	}
    	return firmeza;
	}
	
	private List<Integer> anyadePosVerbos(List<Element> tokens) {
		List<Integer> posVerbos = new ArrayList<Integer>();
		for(int i = 0; i < tokens.size(); i++) {
			String pos = tokens.get(i).getAttributeValue("pos");
			if(pos.equals("verb")) {
	    		posVerbos.add(i);
	    	}
		}
		return posVerbos;
	}
	
	private List<Integer> anyadePosAdverbios(List<Element> tokens){
		List<Integer> posAdverbios = new ArrayList<Integer>();
		for(int i = 0; i < tokens.size(); i++) {
			String pos = tokens.get(i).getAttributeValue("pos");
			if(pos.equals("adverb")) {
	    		posAdverbios.add(i);
	    	}
		}
		return posAdverbios;
	}
	private void esFraseSubordinada() { //Si es subordinada, nos interesa devolver la posicion en la que esta
		esSubordinada=-1;
		for(int i = 0; i < tokens.size(); i++) {
			String tipo = tokens.get(i).getAttributeValue("type");
			if(tipo.equals("subordinating")) {
	    		esSubordinada=i;
	    		break;
	    	}
		}
	}
	private void extraeFrase() {
		for(int i = 0; i < tokens.size(); i++) {
			String palabra = tokens.get(i).getAttributeValue("form");
			if(i == tokens.size()-1 || palabra.equals(",") || palabra.equals(".")) {
				frase+=palabra;
			}
			else {
				frase+=palabra + " "; 
			}
		}
	}
	
	
	private String extraeConclusion(boolean esSubordinada) {
		
		if(esSubordinada) {
			switch(tipoFrase) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				break;
			}
		}
		else {
			if(frase.contains(",")) {
				frase = frase.replace(",", "");
			}
			if(frase.contains(".")) {
				frase = frase.replace(".", "");
			}
			
			switch(tipoFrase) {
			case 1: //afirma	
				conclusion = "Se afirma que " + frase.toLowerCase() + ".";
				break;
			case 2: //niega
				if(adverbiosAquitar.size()>0) {
					String partes[] = frase.split(" ");
					for(int i = 0; i < adverbiosAquitar.size();i++) {
						for(int j = 0; j < partes.length; j++) {
							if(partes[j].toLowerCase().equals(adverbiosAquitar.get(i))) {
								partes[j] = "";
							}
						}
					}
					frase = "";
					for(int i = 0; i < partes.length;i++) {
						frase+=partes[i]+" ";
					}
					frase = frase + ".";
					if(frase.contains("  ")) {
						frase = frase.replace("  ", " ");
					}
					if(frase.contains(" .")) {
						frase = frase.replace(" .", ".");
					}
				}
				conclusion = "Se niega que " + frase.toLowerCase();
				break;
			}
		}
		if(conclusion.contains(" .")) {
			conclusion = conclusion.replace(" .", ".");
		}
		if(conclusion.contains("  ")) {
			conclusion = conclusion.replace("  ", " ");
		}
		
		return conclusion;
	}
	
	private void extraeTokens() {
		File archivo = new File(this.rutaArchivoXML);
		SAXBuilder constructorSAX = new SAXBuilder();
		
		try {
		    Document documento = (Document)constructorSAX.build(archivo);
		    Element nodoRaiz = documento.getRootElement();
		    tokens = nodoRaiz.getChildren("token");
		}catch(Exception e) {
			
		}
	}
	
	
	/**
	 * Regla en frases subordinadas que he seguido para la firmeza
	 * - y - : Afirma
	 * + y + : Afirma
	 * + y - : Niega
	 * - y + : Niega
	 */
	
	public void analisisMorfologicoTweet() {
	    
		if(esSubordinada == -1) { //No estamos ante una frase subordinada, suponemos que es unico verbo
	    	posVerbos = this.anyadePosVerbos(tokens);
		    posAdverbios = this.anyadePosAdverbios(tokens);
	    	firmeza = this.analisis();
	    	if(firmeza.equals("afirma")) {tipoFrase=1;}else {tipoFrase=2;}
	    	conclusion = extraeConclusion(false);
	    	System.out.println(conclusion);
	    }
	    else { //Frase subordinada
	    	List<Element> antes = new ArrayList<Element>();
	    	List<Element> despues = new ArrayList<Element>();
	    	for(int i = 0; i < esSubordinada; i++) {
	    		antes.add(tokens.get(i));
	    	}
	    	for(int i = esSubordinada+1; i<tokens.size();i++) {
	    		despues.add(tokens.get(i));
	    	}
	    	tokens = despues;
	    	posVerbos = this.anyadePosVerbos(antes);
		    posAdverbios = this.anyadePosAdverbios(antes);
	    	String firmezaAntes = this.analisis();
	    	posVerbos = this.anyadePosVerbos(despues);
	    	posAdverbios = this.anyadePosAdverbios(despues);
	    	String firmezaDespues = this.analisis();
	    	
	    	//Ej: Fuentes no confirman que el limón produzca cáncer. Niegan que + despues
	    	//Ej: Fuentes desmienten que el limón produzca cáncer. -> Niegan que + despues
	    	if(firmezaAntes.equals("niega") && firmezaDespues.equals("afirma")) {
	    		firmeza = "niega";
	    		tipoFrase = 1;
	    	//Ej: Fuentes confirman que el limón no produce cáncer. Niegan que + despues sin adverbio negativo
	    	}else if(firmezaAntes.equals("afirma") && firmezaDespues.equals("niega")) {
	    		firmeza = "niega";
	    		tipoFrase = 2;
	    	}
	    	//Ej: Fuentes confirman que el limón produce cáncer. Afirman que + despues
	    	else if(firmezaAntes.equals("afirma") && firmezaDespues.equals("afirma")){
	    		firmeza = "afirma";
	    		tipoFrase = 3;
	    	}
	    	//Ej: Fuentes desmienten que el limón no cure el cáncer. Afirman que + despues sin adverbio negativo
	    	else{ //Este caso firmezaAntes =="niega" && firmezaDespues == "niega"
	    		firmeza = "afirma";
	    		tipoFrase = 4;
	    	}
	    	//conclusion = extraeConclusion(tokens,tipo,true);
	    	System.out.println(conclusion);
	    }
	}
}
	
