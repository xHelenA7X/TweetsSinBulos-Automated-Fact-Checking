package ua.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.util.DbUtil;
import ua.model.Tweet;

public class TweetDao {

    private Connection connection;

    public TweetDao() {
        connection = DbUtil.getConnection();
    }

    public void addTweet(Tweet af) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Tweet(autor,texto,veracidad,fecha_registro) values (?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, af.getAutor());
            preparedStatement.setString(2, af.getTexto());
            preparedStatement.setString(3, af.getVeracidad());
            preparedStatement.setString(4, af.getFecha_registro());
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

    public Tweet getTweetById(int TweetId) {
    	Tweet af = new Tweet();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Tweet where idTweet=?");
            preparedStatement.setInt(1, TweetId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
               // af.setidTweet(rs.getInt("idTweet"));
                af.setAutor(rs.getString("autor"));
                af.setTexto(rs.getString("texto"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return af;
    }
    
}

