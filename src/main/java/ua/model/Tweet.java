package ua.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;
import ua.controller.TweetController;
import ua.dao.TweetDao;
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
	private List<String> IdTweetsRelacionados;
	private static Twitter tw;
	

	public Tweet() {
		tw = TweetConfiguration.getInstance();
	}
    
    public Tweet(String idTweet,String autor, String texto, String idioma,String fecha_publicacion) {
		this.idTweet = idTweet;
		this.autor = autor;
		this.texto = texto;
		this.convierteTextoPlano();
		this.fecha_publicacion = fecha_publicacion;
		//Date myDate = new Date();
		this.fecha_registro= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		IdTweetsRelacionados = new ArrayList<String>();
		this.idioma=idioma;
		tw = TweetConfiguration.getInstance();
		anotaTexto();
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
	
	private void anotaTexto(){
		List<String> nombresComunes = new ArrayList<>();
		String []arrayTexto = this.textoPlano.split(" ");
		
		TreeTaggerWrapper tt = new TreeTaggerWrapper<String>();
		try {
			
			switch(this.idioma){
			case "en":
				tt.setModel("english.par");
			default:
				tt.setModel("spanish.par");
			}
			tt.setHandler(new TokenHandler<String>() {
				public void token(String token, String pos, String lemma) {
						//log.warning(token + "\t" + pos + "\t" + lemma);
						if(pos.equals("NC")) {
							nombresComunes.add(token);
						}
				}
			});
			tt.process(Arrays.asList(arrayTexto));
			buscaTweetsRelacionados(nombresComunes);
		} catch (Exception e) {
			log.warning("Excepcion TreeTagger: " + e.toString());
		}
		finally{
			tt.destroy();
		}		
	}
	
	private void extraeIdTweetsRelacionados(String queryString) {
		try {
			Query query = new Query(queryString);
	        QueryResult result;
	        result = tw.search(query);
	        List<Status> tweets = result.getTweets();
	        
	        List<String> tweetObjects = new ArrayList<String>();
	        for (Status tweet: tweets) {
	        	if(!tweetObjects.contains(tweet.getText())) { //De esta forma controlo que no añada tweets repetidos en la busqueda
	        		//log.warning("@" + tweet.getUser().getScreenName() + " - " +tweet.getCreatedAt()+" - "+ tweet.getText() + " _ " + tweet.getId());
	        		tweetObjects.add(tweet.getText());
	        		String id = Long.toString(tweet.getId());
	        		IdTweetsRelacionados.add(id);	
		        }
	        }
	        
		} catch (TwitterException te) {
            te.printStackTrace();
            log.warning("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	private void buscaTweetsRelacionados(List<String> nombresComunes) {
		String queryString = "";
		String cadenaCompleta="";
		
		for(int i = 0; i < nombresComunes.size(); i++) {
			cadenaCompleta+=nombresComunes.get(i)+" ";
		}
		/**
		if(nombresComunes.size() < 5) {
			int j = 0;
			int i = j+1;
			//log.warning("size: " + nombresComunes.size());
	        while(j < nombresComunes.size()) {
	        	queryString = nombresComunes.get(j) + " " + nombresComunes.get(i);
	        	//log.warning(queryString);
	        	cadenaCompleta+=nombresComunes.get(j) + " ";
	        	//QueryTweets(queryString);
	        	j++;
	        	if(i+1 == nombresComunes.size()){i=0;}
	        	else {i++;}
	        }
		}
		else {
			int j = 0;
			int i = j+1;
			int k = j+2;
			//.warning("size: " + nombresComunes.size());
			while(j < nombresComunes.size()) {
				queryString = nombresComunes.get(j) + " " + nombresComunes.get(i)+ " " + nombresComunes.get(k);
	        	//log.warning(queryString);
	        	cadenaCompleta+=nombresComunes.get(j) + " ";
	        	//QueryTweets(queryString);
	        	j++;
	        	if(i+1 == nombresComunes.size()){i=0;}
	        	else { 
	        		if(k+1 == nombresComunes.size()) {k=0;}
	        	else{
	        		i++;
	        		k++;
	        		}
	        	}
			}
		}
		**/
		extraeIdTweetsRelacionados(cadenaCompleta);
	}
}
