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
                    .prepareStatement("insert into Tweet(idTweet,nombrePerfil,autor,texto,textoPlano,idioma,localizacion,"
                    		+ "veracidad,fecha_registro,fecha_publicacion,tweets_relacionados) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, af.getIdTweet());
            preparedStatement.setString(2, af.getNombrePerfil());
            preparedStatement.setString(3, af.getAutor());
            preparedStatement.setString(4, af.getTexto());
            preparedStatement.setString(5, af.getTextoPlano());
            preparedStatement.setString(6, af.getIdioma());
            preparedStatement.setString(7, af.getLocalizacion());
            preparedStatement.setString(8, af.getVeracidad());
            preparedStatement.setString(9, af.getFecha_registro());
            preparedStatement.setString(10, af.getFecha_publicacion());
            preparedStatement.setString(11, af.getIdTweetsRelacionados().toString());
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

    public List<Tweet> getAllUsers() {
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
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Tweet where idTweet=?");
            preparedStatement.setString(1, TweetId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                af.setIdTweet(rs.getString("idTweet"));
                af.setNombrePerfil(rs.getString("nombrePerfil"));
                af.setAutor(rs.getString("autor"));
                af.setTexto(rs.getString("texto"));
                af.setTextoPlano(rs.getString("textoPlano"));
                af.setIdioma(rs.getString("idioma"));
                af.setLocalizacion(rs.getString("localizacion"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("fecha_registro"));
                af.setFecha_publicacion(rs.getString("fecha_publicacion"));
                af.setIdTweetsRelacionadosString(rs.getString("tweets_relacionados"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return af;
    }
    
}

