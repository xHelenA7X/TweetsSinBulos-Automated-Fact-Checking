package ua.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.model.Palabra;
import ua.util.DbUtil;

public class FuentesFiablesDao {
	private Connection connection;

    public FuentesFiablesDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<String> getAllFuentesFiables() {
        List<String> fuentes = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from FuentesFiables order by idFuentesFiables");
            while (rs.next()) {
            	String fuente = rs.getString("Dominio");
                fuentes.add(fuente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fuentes;
    }
    
}
