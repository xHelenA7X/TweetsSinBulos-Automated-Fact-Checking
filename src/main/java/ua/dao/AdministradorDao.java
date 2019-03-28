package ua.dao;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import ua.model.Administrador;
import ua.util.DbUtil;

public class AdministradorDao {
    private Connection connection;

    public AdministradorDao() {
        connection = DbUtil.getConnection();
    }
    
    public String MD5(String passwordToHash){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public void addAdministrador(Administrador af) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Administrador(idAdministrador,dni,nombre,apellidos,"
                    		+ "contrasenya) values (?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, af.getId());
            preparedStatement.setString(2, af.getDni());
            preparedStatement.setString(3, af.getNombre());
            preparedStatement.setString(4, af.getApellidos());
            preparedStatement.setString(5, this.MD5(af.getContrasenya()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdministrador(String idAdministrador) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from Administrador where idAdministrador=?");
            // Parameters start with 1
            preparedStatement.setString(1, idAdministrador);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAdministrador(Administrador af) {
        try {
            PreparedStatement preparedStatement = connection
            		.prepareStatement("update Administrador set Administrador=?, texto=?, veracidad=?, fecha_registro=?" +
                            "where idAdministrador=?");
            // Parameters start with 1
          //  preparedStatement.setString(1, af.getAdministrador());
          //  preparedStatement.setString(2, af.getTexto());
          //  preparedStatement.setString(3, af.getVeracidad());
          //  preparedStatement.setString(4, af.getFecha_registro());
          //  preparedStatement.setInt(5, af.getidAdministrador());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
    public List<Administrador> getAllAdministradors() {
        List<Administrador> Administradores = new ArrayList<Administrador>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Administrador");
            while (rs.next()) {
                Administrador af = new Administrador();
              //  af.setidAdministrador((rs.getInt("idAdministrador")));
                af.setAdministrador(rs.getString("Administrador"));
                af.setTexto(rs.getString("texto"));
                af.setVeracidad(rs.getString("veracidad"));
                af.setFecha_registro(rs.getString("fecha_registro"));
                Administradores.add(af);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Administradores;
    }
**/
    public Administrador getAdministradorByDni(String AdministradorDni) {
    	Administrador af = new Administrador();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Administrador where dni=?");
            preparedStatement.setString(1, AdministradorDni);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                af.setId(rs.getString("idAdministrador"));
                af.setNombre(rs.getString("nombre"));
                af.setApellidos(rs.getString("apellidos"));
                af.setContrasenya(rs.getString("contrasenya"));
                af.setDni(rs.getString("dni"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return af;
    }
    
}

