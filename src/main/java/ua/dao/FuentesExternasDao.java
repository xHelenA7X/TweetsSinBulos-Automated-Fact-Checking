package ua.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.util.DbUtil;

public class FuentesExternasDao {
	private Connection connection;

    public FuentesExternasDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<String> getAllFuentesFiables() {
        List<String> fuentes = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from FuentesExternas where tipoFuente = 'fiable' order by idFuente");
            while (rs.next()) {
            	String fuente = rs.getString("dominio");
                fuentes.add(fuente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fuentes;
    }
    
    public List<String> getAllFuentesNoFiables() {
        List<String> fuentes = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from FuentesExternas where tipoFuente = 'no_fiable' order by idFuente");
            while (rs.next()) {
            	String fuente = rs.getString("dominio");
                fuentes.add(fuente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fuentes;
    }
    
}
