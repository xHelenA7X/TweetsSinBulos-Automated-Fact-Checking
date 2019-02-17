package ua;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
		    
		    try {
				Status status = tw.showStatus(id_long);
				
				json.put("createdAt", status.getCreatedAt());
				//json.put("userMentionEntities", status.getUserMentionEntities());
				json.put("userName", status.getUser().getName());
				json.put("userLocation", status.getUser().getLocation());
				json.put("text", status.getText());
				json.put("tweetLocation", status.getGeoLocation());
				//json.put("url", status.getURLEntities().toString());
				json.put("contributors", status.getContributors());
				//json.put("hastagh", status.getHashtagEntities());
				//json.put("media", status.getMediaEntities()); Esto es importante
				json.put("languaje", status.getLang());
/**
				JSONArray array = new JSONArray();
				JSONObject item = new JSONObject();
				item.put("text", "test");
				item.put("id", 3);
				item.put("name", "course1");
				array.add(item);

				json.put("course", array);
**/			
				log.warning("status: " + status);
			} catch (TwitterException e) {
				json.put("error", e.getErrorMessage());
			}
			salida = json.toString();
		    JSON(resp, salida);
	  }
}
