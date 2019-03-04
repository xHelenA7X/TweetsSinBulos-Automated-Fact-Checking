package ua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import ua.model.Tweet;

@WebServlet(name="extraetweet",urlPatterns={"/extraetweet"})
public class TweetController extends HttpServlet{
	private static final Logger log = Logger.getLogger(TweetController.class.getName());
	private static ConfigurationBuilder cb;
	private static TwitterFactory f;
	private static Twitter tw;
	private static List<String> nombresComunes;
	
	private static void Tokens() {
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("QmLdCybkqumf2JOQr1E0ZLxWo")
								.setOAuthConsumerSecret("Ze8lAGSxz9NTWNZIbqeOrwzFau4N6b0kAoxwiijNKAHtircdfH")
								.setOAuthAccessToken("1096330876705755136-vq4PO4Oe1kLsMZw6ZUtX7hyC8MgW97")
								.setOAuthAccessTokenSecret("QgDZ8tRc0ZEpMF5R2Rv3veZTrXx4vMZANdCVTMB1jeyPg");
		f = new TwitterFactory(cb.build());
		tw = f.getInstance();
		nombresComunes = new ArrayList<String>();
	}
	
	private String extraerId(String url) {
		int i = url.lastIndexOf('/');
		String idTweet = "";
		//De la url introducida extraemos el id del tuit
		for(int j = i+1; j < url.length(); j++){
			idTweet += url.charAt(j);
        }
		return idTweet;
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
						if(pos.equals("NC")) {
							nombresComunes.add(token);
						}
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

	private void JSON(HttpServletResponse response, String salida) throws ServletException, IOException{
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			String output = "";
			int error = HttpServletResponse.SC_OK;	 
			output = salida; 
			response.setStatus(error);
			PrintWriter out = response.getWriter();
			out.println(output);
	}
	
	private String quitaUrls(String texto){
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
		return nuevoTexto;
	}
	public JSONObject formulaJSON(String id){
		long id_long = Long.parseLong(id);
		String salida = "";
		JSONObject json = new JSONObject();
		boolean existe_url=false;
		
		try {
			Status status = tw.showStatus(id_long);
			
			json.put("createdAt", status.getCreatedAt());
			//json.put("userMentionEntities", status.getUserMentionEntities());
			json.put("userName", status.getUser().getName());
			json.put("userLocation", status.getUser().getLocation());
			json.put("tweetLocation", status.getGeoLocation());
			URLEntity[] urls = status.getURLEntities();
			if(urls != null){
				existe_url = true;
				JSONArray arrayUrls = new JSONArray();
				for(int i = 0; i < urls.length; i++){
					JSONObject item = new JSONObject();
					item.put("url"+(i),urls[i].getExpandedURL());
					arrayUrls.put(item);
				}
				json.put("urls", arrayUrls);
			}
			//json.put("contributors", status.getContributors());
			HashtagEntity[] hashtags = status.getHashtagEntities();
			
			if(hashtags != null){
				JSONArray arrayHashtags = new JSONArray();
				for(int i = 0; i < hashtags.length; i++){
					JSONObject item = new JSONObject();
					item.put("hastagh"+(i), hashtags[i].getText());
					arrayHashtags.put(item);
				}
				json.put("hashtags", arrayHashtags);
			}
			//json.put("media", status.getMediaEntities()); Esto es importante
			String idioma = status.getLang();
			json.put("languaje", idioma);
			json.put("planeText", status.getText());
			
			String texto;
			if(existe_url){
				texto = quitaUrls(status.getText().replace("#", ""));
			}
			else{
				texto = status.getText().replace("#", "");
			}
			json.put("text", texto);
			
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
			
/**
			JSONArray array = new JSONArray();
			JSONObject item = new JSONObject();
			item.put("text", "test");
			item.put("id", 3);
			item.put("name", "course1");
			array.add(item);

			json.put("course", array);
**/				
		} catch (TwitterException e) {
			json.put("error", e.getErrorMessage());
		}
		return json;
	}
	private void QueryTweets(String queryString) {
		try {
			Query query = new Query(queryString);
	        QueryResult result;
	        result = tw.search(query);
	        List<Status> tweets = result.getTweets();
	        for (Status tweet : tweets) {
	            log.warning("@" + tweet.getUser().getScreenName() + " - " +tweet.getCreatedAt()+" - "+ tweet.getText());
	        }
		} catch (TwitterException te) {
            te.printStackTrace();
            log.warning("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	private void buscaTweets() {
		String queryString = "";
		String cadenaCompleta="";
		int j = 0;
		int i = j+1;
        while(j < nombresComunes.size()) {
        	queryString = nombresComunes.get(j) + " " + nombresComunes.get(i);
        	log.warning(queryString);
        	cadenaCompleta+=nombresComunes.get(j);
        	QueryTweets(queryString);
        	j++;
        	if(i+1 == nombresComunes.size()){i=0;}
        	else {i++;}
        }
        QueryTweets(cadenaCompleta);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws IOException, ServletException { 
		log.warning("Hola,peticion get");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Tokens(); //Para registrar el API de Twitter
		 String url = request.getParameter("UrlTweet");
		 String id = extraerId(url);
		 JSONObject json = formulaJSON(id);
		 Tweet tweet = new Tweet(json.getString("userName"),json.getString("planeText"),json.getString("text"),json.getString("createdAt"));
		 buscaTweets();
		 
		 
	     RequestDispatcher view = request.getRequestDispatcher("salida.jsp");
	     request.setAttribute("textoTweet", tweet.getTextoPlano());
	     view.forward(request, response);
		 
	 }
		
}
