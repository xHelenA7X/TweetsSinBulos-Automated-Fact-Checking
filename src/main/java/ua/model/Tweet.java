package ua.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

public class Tweet {
	private String idTweet;
	private String autor;
    private String texto;
    private String fecha_publicacion;
    private String fecha_registro;
    private String veracidad;
    private int idAfirmacion;
    private String textoPlano;
    private String idioma;
	private List<String> IdTweetsRelacionados;
    private static ConfigurationBuilder cb;
	private static TwitterFactory f;
	private static Twitter tw;
	private static List<String> nombresComunes;
	
	public Tweet() {
		
	}
    
    public Tweet(String idTweet,String autor, String texto, String idioma,String fecha_publicacion) {
		this.idTweet = idTweet;
    	this.autor = autor;
		this.texto = texto;
		this.convierteTextoPlano();
		Calendar fecha = new GregorianCalendar();
		this.fecha_publicacion = fecha_publicacion;
		this.fecha_registro = Calendar.DAY_OF_MONTH+"/"+Calendar.MONTH+"/"+Calendar.YEAR;
		IdTweetsRelacionados = new ArrayList<String>();
		this.idioma=idioma;
		anotaTexto();
	}
    
	public String getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(String idTweet) {
		this.idTweet = idTweet;
	}
    
	public int getidAfirmacion() {
		return idAfirmacion;
	}

	public void setidAfirmacion(int id) {
		this.idAfirmacion = id;
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
	
	public int contarPalabras() {
		return this.textoPlano.split(" ").length;
	}

	@Override
	public String toString() {
		return "Afirmacion [idAfirmacion=" + idAfirmacion + ", autor=" + autor + ", texto=" + texto + ", fecha_registro=" + fecha_registro + ", veracidad="
				+ veracidad + "]";
	}
	private void convierteTextoPlano() {
		this.textoPlano = this.texto;
		this.textoPlano = textoPlano.trim();
		this.textoPlano = textoPlano.replace(",", "");
		this.textoPlano = textoPlano.replace(".", "");
		this.textoPlano = textoPlano.replace(":", "");
		this.textoPlano = textoPlano.replace(";", "");
		this.textoPlano = textoPlano.replace("#", "");
	}
	
	private void anotaTexto(){
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
			buscaTweets();
			
		} catch (Exception e) {
			//log.warning("Excepcion: " + e.toString());
		}
		finally{
			tt.destroy();
		}		
	}
	
	private void QueryTweets(String queryString) {
		try {
			Query query = new Query(queryString);
	        QueryResult result;
	        result = tw.search(query);
	        List<Status> tweets = result.getTweets();        

	        for (Status tweet : tweets) {
	            //log.warning("@" + tweet.getUser().getScreenName() + " - " +tweet.getCreatedAt()+" - "+ tweet.getText() + " _ " + tweet.getId());
	            String id = Long.toString(tweet.getId());
	            IdTweetsRelacionados.add(id);
	        }
		} catch (TwitterException te) {
            te.printStackTrace();
            //log.warning("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	private void buscaTweets() {
		String queryString = "";
		String cadenaCompleta="";
		
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
		QueryTweets(cadenaCompleta);
	}
}
