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

				resultSet = metadata.getTables(null, "factchecking", "Afirmacion", null);
				// Si la tabla 'Afirmacion' no existe, la creamos
				if (!resultSet.next()) {
					connection.createStatement().executeUpdate(
					"CREATE TABLE `factchecking`.`Afirmacion` (\n" + 
					"  `idAfirmacion` INT NOT NULL AUTO_INCREMENT,\n" + 
					"  `autor` VARCHAR(45) NULL,\n" + 
					"  `texto` VARCHAR(45) NULL,\n" + 
					"  `veracidad` VARCHAR(45) NULL,\n" + 
					"  `fecha_registro` VARCHAR(45) NULL,\n" + 
					"  PRIMARY KEY (`idAfirmacion`));\n" + 
					"");
				}
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
            return connection;
        }
    }
}
