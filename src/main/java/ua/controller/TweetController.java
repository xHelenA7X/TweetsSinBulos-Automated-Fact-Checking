package ua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import ua.dao.TweetDao;
import ua.model.Tweet;

@WebServlet(name="extraetweet",urlPatterns={"/extraetweet"})
public class TweetController extends HttpServlet{
	private static final Logger log = Logger.getLogger(TweetController.class.getName());
	private static ConfigurationBuilder cb;
	private static TwitterFactory f;
	private static Twitter tw;
	private static List<String> nombresComunes;
	private TweetDao dao;

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
	public TweetController() {
		super();
		 dao = new TweetDao();
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
	
	public JSONObject formulaJSON(Tweet tweet){
		long id_long = Long.parseLong(tweet.getIdTweet());
		String salida = "";
		JSONObject json = new JSONObject();
		boolean existe_url=false;
		
		try {
			Status status = tw.showStatus(id_long);
			
			json.put("fechaCreacion", tweet.getFecha_publicacion());
			json.put("fechaRegistro", tweet.getFecha_registro());
			//json.put("userMentionEntities", status.getUserMentionEntities());
			json.put("usuario", tweet.getAutor());
			json.put("localizacion", tweet.getLocalizacion());
			/**
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
			**/
			/**
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
			**/
			//json.put("media", status.getMediaEntities()); Esto es importante
			json.put("idioma", tweet.getIdioma());
			json.put("textoPlano", tweet.getTextoPlano());
			json.put("texto", tweet.getTexto());
			json.put("veracidad", tweet.getVeracidad());
			
			//insertamos los ids de los tweets relacionados
			JSONArray arrayTweetsRelacionados = new JSONArray();
			for(int i = 0; i < tweet.getIdTweetsRelacionados().size(); i++){
				JSONObject item = new JSONObject();
				item.put("idTweetRelacionado"+(i), tweet.getIdTweetsRelacionados().get(i));
				arrayTweetsRelacionados.put(item);
			}
			json.put("hashtags", arrayTweetsRelacionados);
			
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
	
	
	private Tweet extraeCamposTweet(String id) {
		long id_long = Long.parseLong(id);
		Tweet tweet = null;
		
		try {
			Status status = tw.showStatus(id_long);
			String idTweet = Long.toString(status.getId());
			String nombrePerfil = status.getUser().getScreenName();
			String autor = status.getUser().getName();
			String texto = status.getText();
			String idioma = status.getLang();
			String localizacion = status.getUser().getLocation();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String fechaPublicacion = dateFormat.format(status.getCreatedAt());
			tweet = new Tweet(idTweet,nombrePerfil,autor,texto,idioma,fechaPublicacion,localizacion);
			
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweet;
		
	}


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws IOException, ServletException { 
		String idTweet = req.getParameter("idTweet");
		JSONObject json = new JSONObject();
		
		if(idTweet != null) {
			Tweet tweet = dao.getTweetById(idTweet);
			json = formulaJSON(tweet);
		}
		else {
			json.put("error", "Id del tweet no registrado");
		}
		
		JSON(resp, json.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Tokens();
		 String url = request.getParameter("UrlTweet");
		 String id = extraerId(url);
		 Tweet tweet = dao.getTweetById(id);
		 
		 if(tweet.getIdTweet() == null){
			 //Ese tuit no ha sido registrado todavia
			 tweet = extraeCamposTweet(id);
			 dao.addTweet(tweet);
		 }
		 
	     RequestDispatcher view = request.getRequestDispatcher("salida.jsp");
	     request.setAttribute("idTweet", id);
	     view.forward(request, response);
		 
	 }
		
}
