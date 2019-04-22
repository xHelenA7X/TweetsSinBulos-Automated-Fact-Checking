package ua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.model.Adverbio;
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
}
