package ua.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.model.Tweet;
import ua.util.DbUtil;

public class TweetDao {

    private Connection connection;

    public TweetDao() {
        connection = DbUtil.getConnection();
    }

    public void addTweet(Tweet af) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Tweet(idTweet,texto,textoPlano,idioma,"
                    		+ "veracidad,fecha_registro,fecha_publicacion,tweets_relacionados,autor,conclusion,tituloNoticia,cuerpoNoticia,linkNoticia,veracidadNoticia,salidaCorpus,fuenteNoticia)"
                    		+ " values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, af.getIdTweet());
            preparedStatement.setString(2, af.getTexto());
            preparedStatement.setString(3, af.getTextoPlano());
            preparedStatement.setString(4, af.getIdioma());
            preparedStatement.setString(5, af.getVeracidad());
            preparedStatement.setString(6, af.getFecha_registro());
            preparedStatement.setString(7, af.getFecha_publicacion());
            preparedStatement.setString(8, af.getIdTweetsRelacionados().toString());
            preparedStatement.setString(9, af.getAutor());
            preparedStatement.setString(10, af.getConclusion());
            preparedStatement.setString(11, af.getTituloNoticia());
            preparedStatement.setString(12, af.getCuerpoNoticia());
            preparedStatement.setString(13, af.getLinkNoticia());
            preparedStatement.setString(14, af.getVeracidadNoticia());
            preparedStatement.setString(15, af.getSalidaCorpus());
            preparedStatement.setString(16, af.getFuenteNoticia());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTweet(int idTweet) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from Tweet where idTweet=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idTweet);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTweet(Tweet af) {
        try {
            PreparedStatement preparedStatement = connection
            		.prepareStatement("update Tweet set autor=?, texto=?, veracidad=?, fecha_registro=?" +
                            "where idTweet=?");
            // Parameters start with 1
            preparedStatement.setString(1, af.getAutor());
            preparedStatement.setString(2, af.getTexto());
            preparedStatement.setString(3, af.getVeracidad());
            preparedStatement.setString(4, af.getFecha_registro());
          //  preparedStatement.setInt(5, af.getidTweet());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tweet> getAllTweets() {
        List<Tweet> Tweetes = new ArrayList<Tweet>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Tweet");
            while (rs.next()) {
                Tweet af = new Tweet();
              //  af.setidTweet((rs.getInt("idTweet")));
                af.setAutor(rs.getString("autor"));
                af.setTexto(rs.getString("texto"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("fecha_registro"));
                Tweetes.add(af);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Tweetes;
    }

    public Tweet getTweetById(String TweetId) {
    	Tweet af = new Tweet();
    	NoticiaFuentesExternasDao ndao = new NoticiaFuentesExternasDao();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Tweet where idTweet=?");
            preparedStatement.setString(1, TweetId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                af.setIdTweet(rs.getString("idTweet"));
                af.setAutor(rs.getString("autor"));
                af.setTexto(rs.getString("texto"));
                af.setTextoPlano(rs.getString("textoPlano"));
                af.setIdioma(rs.getString("idioma"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("fecha_registro"));
                af.setFecha_publicacion(rs.getString("fecha_publicacion"));
                af.setIdTweetsRelacionadosString(rs.getString("tweets_relacionados"));
                af.setConclusion(rs.getString("conclusion"));
                af.setTituloNoticia(rs.getString("tituloNoticia"));
                af.setCuerpoNoticia(rs.getString("cuerpoNoticia"));
                af.setLinkNoticia(rs.getString("linkNoticia"));
                af.setVeracidadNoticia(rs.getString("veracidadNoticia"));
                af.setSalidaCorpus(rs.getString("salidaCorpus"));
                af.setFuenteNoticia(rs.getString("fuenteNoticia"));
                af.setFuentesExternas(ndao.getNoticiasFuentesExternasByIdTweet(TweetId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return af;
    }
    
}

