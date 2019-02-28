package ua.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.util.DbUtil;
import ua.model.Afirmacion;;

public class AfirmacionDao {

    private Connection connection;

    public AfirmacionDao() {
        connection = DbUtil.getConnection();
    }

    public void addAfirmacion(Afirmacion af) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Afirmacion(autor,texto,veracidad,fecha_registro) values (?, ?, ?, ? )");
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

    public void deleteAfirmacion(int idAfirmacion) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from Afirmacion where idAfirmacion=?");
            // Parameters start with 1
            preparedStatement.setInt(1, idAfirmacion);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAfirmacion(Afirmacion af) {
        try {
            PreparedStatement preparedStatement = connection
            		.prepareStatement("update Afirmacion set autor=?, texto=?, veracidad=?, fecha_registro=?" +
                            "where idAfirmacion=?");
            // Parameters start with 1
            preparedStatement.setString(1, af.getAutor());
            preparedStatement.setString(2, af.getTexto());
            preparedStatement.setString(3, af.getVeracidad());
            preparedStatement.setString(4, af.getFecha_registro());
            preparedStatement.setInt(5, af.getidAfirmacion());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Afirmacion> getAllUsers() {
        List<Afirmacion> afirmaciones = new ArrayList<Afirmacion>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Afirmacion");
            while (rs.next()) {
                Afirmacion af = new Afirmacion();
                af.setidAfirmacion((rs.getInt("idAfirmacion")));
                af.setAutor(rs.getString("autor"));
                af.setTexto(rs.getString("texto"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("fecha_registro"));
                afirmaciones.add(af);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return afirmaciones;
    }

    public Afirmacion getAfirmacionById(int afirmacionId) {
    	Afirmacion af = new Afirmacion();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Afirmacion where idAfirmacion=?");
            preparedStatement.setInt(1, afirmacionId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                af.setidAfirmacion(rs.getInt("idAfirmacion"));
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

