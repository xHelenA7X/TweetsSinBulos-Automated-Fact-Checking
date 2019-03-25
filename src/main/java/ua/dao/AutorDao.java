package ua.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.model.Autor;
import ua.util.DbUtil;

public class AutorDao {

    private Connection connection;

    public AutorDao() {
        connection = DbUtil.getConnection();
    }

    public void addAutor(Autor af) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Autor(idAutor,nombrePerfil,alias,descripcion,"
                    		+ "localizacion,cuentaVerificada,temaPorDefecto,imagenPorDefecto,antesBulo) values (?,?, ?, ?, ?, ?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, af.getIdAutor());
            preparedStatement.setString(2, af.getNombrePerfil());
            preparedStatement.setString(3, af.getAlias());
            preparedStatement.setString(4, af.getDescripcion());
            preparedStatement.setString(5, af.getLocalizacion());
            preparedStatement.setString(6, af.getCuentaVerificada());
            preparedStatement.setString(7, af.getTemaPorDefecto());
            preparedStatement.setString(8, af.getImagenPorDefecto());
            preparedStatement.setString(9, af.getAntesBulo());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAutor(String idAutor) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from Autor where idAutor=?");
            // Parameters start with 1
            preparedStatement.setString(1, idAutor);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAutor(Autor af) {
        try {
            PreparedStatement preparedStatement = connection
            		.prepareStatement("update Autor set autor=?, texto=?, veracidad=?, fecha_registro=?" +
                            "where idAutor=?");
            // Parameters start with 1
          //  preparedStatement.setString(1, af.getAutor());
          //  preparedStatement.setString(2, af.getTexto());
          //  preparedStatement.setString(3, af.getVeracidad());
          //  preparedStatement.setString(4, af.getFecha_registro());
          //  preparedStatement.setInt(5, af.getidAutor());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
    public List<Autor> getAllAutors() {
        List<Autor> Autores = new ArrayList<Autor>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Autor");
            while (rs.next()) {
                Autor af = new Autor();
              //  af.setidAutor((rs.getInt("idAutor")));
                af.setAutor(rs.getString("autor"));
                af.setTexto(rs.getString("texto"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("fecha_registro"));
                Autores.add(af);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Autores;
    }
**/
    public Autor getAutorById(String AutorId) {
    	Autor af = new Autor();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Autor where idAutor=?");
            preparedStatement.setString(1, AutorId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                af.setIdAutor(rs.getString("idAutor"));
                af.setNombrePerfil(rs.getString("nombrePerfil"));
                af.setAlias(rs.getString("alias"));
                af.setDescripcion(rs.getString("descripcion"));
                af.setLocalizacion(rs.getString("localizacion"));
                af.setCuentaVerificada(rs.getString("cuentaVerificada"));
                af.setTemaPorDefecto(rs.getString("temaPorDefecto"));
                af.setImagenPorDefecto(rs.getString("imagenPorDefecto"));
                af.setAntesBulo(rs.getString("antesBulo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return af;
    }
    
}

