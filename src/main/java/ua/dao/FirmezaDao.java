package ua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.model.Adverbio;
import ua.model.Tweet;
import ua.model.Verbo;
import ua.util.DbUtil;

public class FirmezaDao {
	private Connection connection;

    public FirmezaDao() {
        connection = DbUtil.getConnection();
    }
    public Adverbio getAdverbioByNombre(String adverbio) {
    	Adverbio adv = new Adverbio();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from AdverbiosDeclarativos where adverbio=?");
            preparedStatement.setString(1, adverbio);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	adv.setAdverbio(rs.getString("adverbio"));
            	adv.setFirmeza(rs.getString("firmeza"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adv;
    }
    public Verbo getVerboByNombre(String verbo) {
    	Verbo vb = new Verbo();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from VerbosDeclarativos where verbo=?");
            preparedStatement.setString(1, verbo);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	vb.setVerbo(rs.getString("verbo"));
            	vb.setFirmeza(rs.getString("firmeza"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vb;
    }
    
    public List<Verbo> getAllVerbos() {
        List<Verbo> verbos = new ArrayList<Verbo>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from VerbosDeclarativos");
            while (rs.next()) {
                Verbo vb = new Verbo();
                vb.setVerbo(rs.getString("verbo"));
                vb.setFirmeza(rs.getString("firmeza"));
                verbos.add(vb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verbos;
    }
    public List<Adverbio> getAllAdverbios() {
        List<Adverbio> adverbios = new ArrayList<Adverbio>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from AdverbiosDeclarativos");
            while (rs.next()) {
            	Adverbio vb = new Adverbio();
                vb.setAdverbio(rs.getString("adverbio"));
                vb.setFirmeza(rs.getString("firmeza"));
                adverbios.add(vb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adverbios;
    }
}
