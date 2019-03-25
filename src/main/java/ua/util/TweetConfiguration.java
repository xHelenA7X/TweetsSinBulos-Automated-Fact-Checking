package ua.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetConfiguration {
	private static ConfigurationBuilder cb;
	private static TwitterFactory f;
	private static Twitter tw = null;
	
	public static Twitter getInstance() {
		if(tw != null) {
			return tw;
		}
		else {
			cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("QmLdCybkqumf2JOQr1E0ZLxWo")
									.setOAuthConsumerSecret("Ze8lAGSxz9NTWNZIbqeOrwzFau4N6b0kAoxwiijNKAHtircdfH")
									.setOAuthAccessToken("1096330876705755136-vq4PO4Oe1kLsMZw6ZUtX7hyC8MgW97")
									.setOAuthAccessTokenSecret("QgDZ8tRc0ZEpMF5R2Rv3veZTrXx4vMZANdCVTMB1jeyPg");
			f = new TwitterFactory(cb.build());
			tw = f.getInstance();
			return tw;
		}
	}
}
