package ua;

import java.sql.*;



public class Main {
	public String[] tratarFicheroEntrada(){
		String []arrayTexto = null;
		try
		{
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/factchecking","helena", "1234");
		   Statement s = conexion.createStatement(); 
		   ResultSet rs = s.executeQuery ("select * from texto_entrada");
		   
		   String texto = "";
		   while (rs.next()) 
		   { 
			   texto = rs.getString(1);  
		   }
		   if(texto != ""){
			   texto = texto.trim();
			   texto = texto.replace(",", "");
			   texto = texto.replace(".", "");
			   texto = texto.replace(":", "");
			   texto = texto.replace(";", "");
			   arrayTexto = texto.split(" ");
		   }
		   conexion.close();
		   
		} catch (Exception e)
		{
		   e.printStackTrace();
		}
		return arrayTexto;
	}
	
	public void printArray(String[] arrayTexto){
		if(arrayTexto != null){
			for(int i = 0; i < arrayTexto.length; i++){
				System.out.println("Posicion " + i + ": " + arrayTexto[i]);
			}
		}else{
			System.out.println("Nada que leer.");
		}
	}
	
	public static void main(String [] args){
		Main m = new Main();
		String []arrayTexto = m.tratarFicheroEntrada();
		m.printArray(arrayTexto);
	}
}
