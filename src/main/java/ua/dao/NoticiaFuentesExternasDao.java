package ua.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.model.NoticiaFuenteExterna;
import ua.model.Tweet;
import ua.util.DbUtil;

public class NoticiaFuentesExternasDao {
	private Connection connection;

    public NoticiaFuentesExternasDao() {
        connection = DbUtil.getConnection();
    }
    
    public void addNoticiaFuenteExterna(NoticiaFuenteExterna fuente, String idTweet) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into NoticiasFuentesExternas(titulo,link,cuerpo,"
                    		+ "dominio,idTweet) values (?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, fuente.toString(fuente.getTitulo()));
            preparedStatement.setString(2, fuente.toString(fuente.getLink()));
            preparedStatement.setString(3, fuente.toString(fuente.getCuerpo()));
            preparedStatement.setString(4, fuente.getFuente());
            preparedStatement.setString(5, idTweet);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String[] stringToVector(String cadena) {
    	String[] vector = new String[2];
    	
    	int index = 2;
    	int index_despues = cadena.indexOf("\"", index);
    	
    	String primer_elemento="";
		primer_elemento+=cadena.substring(index,index_despues);
		
		index = cadena.indexOf("\"",index_despues+1);
		index_despues = cadena.indexOf("\"", index+1);
		
		String segundo_elemento="";
		segundo_elemento+=cadena.substring(index+1,index_despues);
		
		vector[0] = primer_elemento;
		vector[1] = segundo_elemento;
		
		return vector;
    }
    
    public List<NoticiaFuenteExterna> getNoticiasFuentesExternasByIdTweet(String idTweet){
    	List<NoticiaFuenteExterna> noticias = new ArrayList<NoticiaFuenteExterna>();
    	
    	try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from NoticiasFuentesExternas where idTweet="+idTweet);
            while (rs.next()) {
                NoticiaFuenteExterna fuente = new NoticiaFuenteExterna();
                fuente.setTitulo(this.stringToVector(rs.getString("titulo")));
                fuente.setLink(this.stringToVector(rs.getString("link")));
                fuente.setCuerpo(this.stringToVector(rs.getString("cuerpo")));
                fuente.setFuente(rs.getString("dominio"));
                noticias.add(fuente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return noticias;
    }
}
