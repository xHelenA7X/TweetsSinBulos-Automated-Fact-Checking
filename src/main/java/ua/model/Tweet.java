package ua.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerWrapper;

import twitter4j.HashtagEntity;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;
import ua.controller.TweetController;
import ua.dao.AutorDao;
import ua.dao.FuentesFiablesDao;
import ua.dao.TweetDao;
import ua.util.FreelingXML;
import ua.util.TweetConfiguration;

public class Tweet {
	private static final Logger log = Logger.getLogger(TweetController.class.getName());
	private String idTweet;
	private String autor;
    private String texto;
    private String fecha_publicacion;
    private String fecha_registro;
    private String veracidad;
    private String textoPlano;
    private String idioma;
    private String conclusion;
	private List<String> IdTweetsRelacionados;
	private static Twitter tw;
	private AnalisisMorfologico analisis;
	private CorpusFakeNews corpus;
	private String salidaCorpus;
	private String cuerpoNoticia;
	private String tituloNoticia;
	private String linkNoticia;
	private String veracidadNoticia;
	private String fuenteNoticia;
	private String keywords_str;


	public Tweet() {
		tw = TweetConfiguration.getInstance();
	}
    
    public Tweet(String idTweet,String autor, String texto, String idioma,String fecha_publicacion) {
		this.idTweet = idTweet;
		this.autor = autor;
		this.texto = texto;
		this.conclusion = "";
		this.salidaCorpus="";
		cuerpoNoticia="";
		tituloNoticia="";
		linkNoticia="";
		veracidadNoticia="";
		fuenteNoticia="";
		keywords_str = "";
		this.convierteTextoPlano();
		this.fecha_publicacion = fecha_publicacion;
		//Date myDate = new Date();
		this.fecha_registro= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		IdTweetsRelacionados = new ArrayList<String>();
		this.idioma=idioma;
		tw = TweetConfiguration.getInstance();
		generaFicheros();
		generaSalida(); //Analisis morfologico de la oracion, extraer nombres comunes, propios, verbos...
		busquedaCorpusFakeNews();
		BusquedaFuentesFiablesExternas();
	}
    
	public String getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(String idTweet) {
		this.idTweet = idTweet;
	}    
    
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getTextoPlano() {
		return textoPlano;
	}

	public void setTextoPlano(String texto) {
		this.textoPlano = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(String fecha) {
		this.fecha_registro = fecha;
	}
	
	public String getFecha_publicacion() {
		return fecha_publicacion;
	}
	
	public void setFecha_publicacion(String fecha) {
		this.fecha_publicacion= fecha;
	}

	public String getVeracidad() {
		return veracidad;
	}

	public void setVeracidad(String veracidad) {
		this.veracidad = veracidad;
	}
	
	public List<String> getIdTweetsRelacionados() {
		return IdTweetsRelacionados;
	}

	public void setIdTweetsRelacionados(List<String> tweets) {
		this.IdTweetsRelacionados = tweets;
	}
	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	public String getSalidaCorpus() {
		return salidaCorpus;
	}

	public void setSalidaCorpus(String salidaCorpus) {
		this.salidaCorpus = salidaCorpus;
	}

	public String getCuerpoNoticia() {
		return cuerpoNoticia;
	}

	public void setCuerpoNoticia(String cuerpoNoticia) {
		this.cuerpoNoticia = cuerpoNoticia;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getLinkNoticia() {
		return linkNoticia;
	}

	public void setLinkNoticia(String linkNoticia) {
		this.linkNoticia = linkNoticia;
	}

	public String getVeracidadNoticia() {
		return veracidadNoticia;
	}

	public void setVeracidadNoticia(String veracidadNoticia) {
		this.veracidadNoticia = veracidadNoticia;
	}
	public String getFuenteNoticia() {
		return veracidadNoticia;
	}

	public void setFuenteNoticia(String fuenteNoticia) {
		this.fuenteNoticia = fuenteNoticia;
	}

	
	public void setIdTweetsRelacionadosString(String tweets) {
		ArrayList<String> p = new ArrayList<>();
		String[] lista = tweets.split(" ");
		
		for(int index=0; index < lista.length; index++) {
			String palabra = lista[index].replace(",", "").replace("[", "").replace("]", "").replace(" ", "");
			p.add(palabra);
		}
		
		this.IdTweetsRelacionados = p;
	}
	
	
	public int contarPalabras() {
		return this.textoPlano.split(" ").length;
	}
	
	@Override
	public String toString() {
		return "Afirmacion [idTweet=" + idTweet + ", autor=" + autor + ", texto=" + texto + ", fecha_registro=" + fecha_registro + ", veracidad="
				+ veracidad + "]";
	}
	private void quitaUrls(String texto){
		String nuevoTexto = "";
		String[] arrayTexto = texto.split(" ");
		
		for(int i = 0;i < arrayTexto.length; i++){
			if(arrayTexto[i].contains("http")){
				i++;
			}
			else{
				if(i != arrayTexto.length -1){
					nuevoTexto+= arrayTexto[i] + " ";
				}
				else{
					nuevoTexto+= arrayTexto[i];
				}
			}
		}
		texto = nuevoTexto;
	}
	private void convierteTextoPlano() {
		if(this.texto.contains("http")) {
			quitaUrls(this.texto);
		}
		this.textoPlano = this.texto;
		this.textoPlano = textoPlano.trim();
		this.textoPlano = textoPlano.replace(",", "");
		this.textoPlano = textoPlano.replace(".", "");
		this.textoPlano = textoPlano.replace(":", "");
		this.textoPlano = textoPlano.replace(";", "");
		this.textoPlano = textoPlano.replace("#", "");
	}
	private void generaFicheros() {
		//Primero hay que generar los archivos para el analisis
		try {
			FreelingXML.generaFicheroEntrada(idTweet, textoPlano);
			FreelingXML.generaFicheroSalida(idTweet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private String getNombreAutor(String id) {
		AutorDao dao = new AutorDao();
		Autor autor = dao.getAutorById(id);
		return autor.getAlias();
	}
	
	private List<String> unirListas(List<String> listOne, List<String> listTwo) {
		List<String> unificada = new ArrayList<String>();
		for(int i = 0; i < listOne.size(); i++) {
			unificada.add(listOne.get(i));
		}
		for(int i = 0; i < listTwo.size(); i++) {
			unificada.add(listTwo.get(i));
		}
		return unificada;
	}
	
	private void generaSalida(){
		analisis = new AnalisisMorfologico("/etc/tweets/"+idTweet+"-salida.xml",this.getNombreAutor(autor));
		analisis.analisisMorfologicoTweet();
		this.setConclusion(analisis.getConclusion());
		
		buscaTweetsRelacionados(unirListas(analisis.getNombresComunes(), analisis.getVerbosConjugados())); //Ahora nombres comunes y verbo
		buscaTweetsRelacionados(unirListas(analisis.getNombresPropios(), analisis.getNombresComunes()));
		buscaTweetsRelacionados(unirListas(analisis.getNombresComunes(), analisis.getAdjetivos()));
	}
	
	private void extraeKeywordsString(List<String> keywords) {
		for(int i = 0; i < keywords.size(); i++) {
			if(i != keywords.size()-1) {
				keywords_str+=keywords.get(i) + " ";
			}
			else {
				keywords_str+=keywords.get(i);
			}
		}
	}
	
	private void busquedaCorpusFakeNews() {
		corpus = new CorpusFakeNews();
		List<String> keywords = unirListas(analisis.getNombresComunes(), analisis.getNombresPropios());
		
		if(analisis.getAdjetivos().size()>0) {
			keywords = unirListas(keywords,analisis.getAdjetivos());
		}
		
		extraeKeywordsString(keywords);
		
		for(int i = 0; i < keywords.size(); i++) { //Busqueda de las keywords en el corpus
			corpus.busquedaCorpusFakeNews(keywords.get(i));
		}
		List<Integer> coincidencias = new ArrayList<Integer>();
		List<KeywordsCorpus> listKeywords = corpus.getKeywordsCorpus();
		if(listKeywords.size()>0) {
			
			for(int i = 0; i < listKeywords.size(); i++) {
				int coincide = 0;
				int fila = listKeywords.get(i).getFila();
				for(int j = 0; j < listKeywords.size(); j++) {
					int fila2 = listKeywords.get(j).getFila();
					if(fila == fila2) {
						coincide++;
					}
				}
				//Almacenamos la fila que tiene mas coincidencias con las keywords
				coincidencias.add(coincide);
			}
			
			int maximo_valor = Collections.max(coincidencias);
			int index_of_maximo = coincidencias.indexOf(maximo_valor);
			int numKeywords = keywords.size();
			//Sacamos el porcentaje de coincidencia, minimo tienen que coincidir en un 70%
			double coincidencia = (maximo_valor*100)/numKeywords;
			
			salidaCorpus = "No se ha encontrado coincidencia en el corpus.";
			
			boolean entra = false;
			if (analisis.esSubordinada()){
				if(coincidencia > 60.0) { //Si es subordinada relajamos la restriccion
					entra = true;
				}
			}
			else {
				if(coincidencia > 70.0) {
					entra = true;
				}
			}
			
			if(entra) { //Coincidencia encontrada
				int fila_noticia = listKeywords.get(index_of_maximo).getFila();
				tituloNoticia=corpus.getTituloByFila(fila_noticia);
				//Analizar la posicion que da el titulo de la noticia
				AnalisisMorfologico analisis = new AnalisisMorfologico();
				String posicion_titulo = analisis.analisisFrase(tituloNoticia);	
				cuerpoNoticia=corpus.getTextoNoticiaByFila(fila_noticia);
				linkNoticia=corpus.getLinkByFila(fila_noticia);
				veracidadNoticia = corpus.getVeracidadByFila(fila_noticia);
				fuenteNoticia = corpus.getFuenteByFila(fila_noticia);
				salidaCorpus="Noticia con procedencia " + fuenteNoticia + " encontrada.";
				
				if(conclusion.contains("afirma")) {
					if(posicion_titulo.equals("afirma")) {
						if(veracidadNoticia.equals("True")) {
							veracidad = "1.0";
						}
						else {
							veracidad = "0.0";
						}
					}
					else {
						if(veracidadNoticia.equals("True")) {
							veracidad = "0.0";
						}
						else {
							veracidad = "1.0";
						}
					}
				}
				else if(conclusion.contains("niega")) {
					if(posicion_titulo.equals("niega")) {
						if(veracidadNoticia.equals("True")) {
							veracidad = "1.0";
						}
						else {
							veracidad = "0.0";
						}
					}
					else {
						if(veracidadNoticia.equals("True")) {
							veracidad = "0.0";
						}
						else {
							veracidad = "1.0";
						}
					}
				}
			}
		}
	}
	
	public String BusquedaFuentesFiablesExternas() {
        // Contiene la instruccion a ejecutar...
        // Esta instruccion podria ser cambiada por cualquier otra
        FuentesFiablesDao dao = new FuentesFiablesDao();
        List<String> fuentes = dao.getAllFuentesFiables();
        String comando = "";
        String salida = "";
        
        for(int i = 0; i < fuentes.size(); i++) {
	        salida = FuentesFiables.Busqueda(fuentes.get(i), keywords_str);
	        log.warning(salida);
        }
        log.warning(salida);
        return salida;
	}
	
	private void extraeIdTweetsRelacionados(String queryString) {
		try {
			Query query = new Query();
			query.setQuery(queryString);
			query.resultType(ResultType.mixed);
			//query.setCount(20);
	        QueryResult result;
	        result = tw.search(query);
	        List<Status> tweets = result.getTweets();
	        
	        List<String> tweetObjects = new ArrayList<String>();
	        for (Status tweet: tweets) {
	        	if(!tweetObjects.contains(tweet.getText())) { //De esta forma controlo que no añada tweets repetidos en la busqueda
	        		//log.warning("@" + tweet.getUser().getScreenName() + " - " +tweet.getCreatedAt()+" - "+ tweet.getText() + " _ " + tweet.getId());
	        		tweetObjects.add(tweet.getText());
	        		String id = Long.toString(tweet.getId());
	        		if(!IdTweetsRelacionados.contains(id) && !Long.toString(tweet.getUser().getId()).equals(autor)) { //Si ya esta en el array que no lo vuelva a añadir, o si el tweet proviene del mismo usuario
	        			IdTweetsRelacionados.add(id);	
	        		}
	        		
		        }
	        } 
	        
		} catch (TwitterException te) {
            te.printStackTrace();
            log.warning("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	private void buscaTweetsRelacionados(List<String> keywords) {
		String queryString = "";
		String cadenaCompleta="";
		if(keywords.size()>0) {
		for(int i = 0; i < keywords.size(); i++) {
			cadenaCompleta+=keywords.get(i)+" ";
		}
		extraeIdTweetsRelacionados(cadenaCompleta);
		}
	}
}
