package ua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.model.Palabra;
import ua.model.Tweet;
import ua.util.DbUtil;

public class FirmezaDao {
	private Connection connection;

    public FirmezaDao() {
        connection = DbUtil.getConnection();
    }
    public Palabra getAdverbioByNombre(String adverbio) {
    	Palabra adv = new Palabra();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from AdverbiosDeclarativos where adverbio=?");
            preparedStatement.setString(1, adverbio);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	adv.setPalabra(rs.getString("adverbio"));
            	adv.setFirmeza(rs.getString("firmeza"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adv;
    }
    public Palabra getVerboByNombre(String verbo) {
    	Palabra vb = new Palabra();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from VerbosDeclarativos where verbo=?");
            preparedStatement.setString(1, verbo);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	vb.setPalabra(rs.getString("verbo"));
            	vb.setFirmeza(rs.getString("firmeza"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vb;
    }
    
    public Palabra getAdjetivoByNombre(String adjetivo) {
    	Palabra adj = new Palabra();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from AdjetivosDeclarativos where adjetivo=?");
            preparedStatement.setString(1, adjetivo);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	adj.setPalabra(rs.getString("adjetivo"));
            	adj.setFirmeza(rs.getString("firmeza"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adj;
    }
    
    public List<Palabra> getAllVerbos() {
        List<Palabra> verbos = new ArrayList<Palabra>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from VerbosDeclarativos");
            while (rs.next()) {
            	Palabra vb = new Palabra();
                vb.setPalabra(rs.getString("verbo"));
                vb.setFirmeza(rs.getString("firmeza"));
                verbos.add(vb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verbos;
    }
    public List<Palabra> getAllAdverbios() {
        List<Palabra> adverbios = new ArrayList<Palabra>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from AdverbiosDeclarativos");
            while (rs.next()) {
            	Palabra vb = new Palabra();
                vb.setPalabra(rs.getString("adverbio"));
                vb.setFirmeza(rs.getString("firmeza"));
                adverbios.add(vb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adverbios;
    }
    
    public List<Palabra> getAllAdjetivos() {
        List<Palabra> adjetivos = new ArrayList<Palabra>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from AdjetivosDeclarativos");
            while (rs.next()) {
            	Palabra adj = new Palabra();
            	adj.setPalabra(rs.getString("adjetivo"));
            	adj.setFirmeza(rs.getString("firmeza"));
                adjetivos.add(adj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adjetivos;
    }
}
