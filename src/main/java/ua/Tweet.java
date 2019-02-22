package ua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.HashtagEntity;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

@WebServlet(name="extraetweet",urlPatterns={"/extraetweet"})
public class Tweet extends HttpServlet{
	private static final Logger log = Logger.getLogger(Tweet.class.getName());
	
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
	
		@Override
		  public void doGet(HttpServletRequest req, HttpServletResponse resp)
		              throws IOException, ServletException { 
		    
			String id = req.getParameter("id");
		    ConfigurationBuilder cb = new ConfigurationBuilder();
		    cb.setDebugEnabled(true).setOAuthConsumerKey("QmLdCybkqumf2JOQr1E0ZLxWo")
		    						.setOAuthConsumerSecret("Ze8lAGSxz9NTWNZIbqeOrwzFau4N6b0kAoxwiijNKAHtircdfH")
		    						.setOAuthAccessToken("1096330876705755136-vq4PO4Oe1kLsMZw6ZUtX7hyC8MgW97")
		    						.setOAuthAccessTokenSecret("QgDZ8tRc0ZEpMF5R2Rv3veZTrXx4vMZANdCVTMB1jeyPg");
		    
		    TwitterFactory f = new TwitterFactory(cb.build());
		    Twitter tw = f.getInstance();
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
				json.put("languaje", status.getLang());
				
				String texto;
				if(existe_url){
					texto = quitaUrls(status.getText().replace("#", ""));
				}
				else{
					texto = status.getText().replace("#", "");
				}
				json.put("text", texto);
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
			salida = json.toString();
		    JSON(resp, salida);
	  }
}
