package ua.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost/factchecking?useSSL=false";
				String user = "helena";
				String password = "1234";
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }   
            
            ResultSet resultSet = null;
			DatabaseMetaData metadata;
			try {
				metadata = connection.getMetaData();

				resultSet = metadata.getTables(null, "factchecking", "Tweet", null);
				// Si la tabla 'Tweet' no existe, inicializamos la bd
				if (!resultSet.next()) {
					connection.createStatement().executeUpdate(
					"CREATE TABLE `factchecking`.`Autor` (\n" + 
					"  `nombrePerfil` varchar(45) NOT NULL,\n" +
					"  `idAutor` varchar(50) NOT NULL,\n" +
					"  `alias` VARCHAR(50) NOT NULL,\n" + 
					"  `descripcion` varchar(500) NOT NULL,\n"+
					"  `localizacion` VARCHAR(45),\n" + 
					"  `cuentaVerificada` VARCHAR(5) NOT NULL,\n" +
					"  `temaPorDefecto` varchar(5) NOT NULL,"+
					"  `imagenPorDefecto` varchar(5) NOT NULL,"+
					"  `antesBulo` VARCHAR(5) NOT NULL,\n" +
					"   PRIMARY KEY (`nombrePerfil`));\n"
					+		
					"CREATE TABLE `factchecking`.`Tweet` (\n" + 
					"  `idTweet` VARCHAR(50) NOT NULL,\n" + 
					"  `nombrePerfil` VARCHAR(45) NOT NULL,\n" + 
					"  `texto` VARCHAR(500) NOT NULL,\n" +
					"  `textoPlano` VARCHAR(500) NOT NULL,\n" +
					"  `idioma` VARCHAR(2) NOT NULL,\n" + 
					"  `veracidad` VARCHAR(45),\n" + 
					"  `fecha_registro` VARCHAR(45) NOT NULL,\n" +
					"  `fecha_publicacion` VARCHAR(45) NOT NULL,\n" +
					"  `tweets_relacionados` VARCHAR(500) NOT NULL,\n" +
					"   `fk_autor` varchar(45) NOT NULL,\n" +
					"	PRIMARY KEY (`idTweet`)\n"+
					"	KEY `fk_autor_idx` (`fk_autor`),\n" +
					"   CONSTRAINT `fk_autor` FOREIGN KEY (`fk_autor`) REFERENCES `Autor` (`nombrePerfil`) ON DELETE NO ACTION ON UPDATE NO ACTION"+
					"   );\n"
					);
				}
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
            return connection;
        }
    }
}
