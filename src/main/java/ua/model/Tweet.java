package ua.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tweet {
	private String autor;
    private String texto;
    private String fecha_publicacion;
    private String fecha_registro;
    private String veracidad;
    private int idAfirmacion;
    private String textoPlano;
    
    public Tweet() {
    	
    }
    
    public Tweet(String autor, String textoPlano, String texto, String fecha_publicacion) {
		this.autor = autor;
		this.textoPlano = textoPlano;
		this.texto = texto;
		Calendar fecha = new GregorianCalendar();
		this.fecha_publicacion = fecha_publicacion;
		this.fecha_registro = Calendar.DAY_OF_MONTH+"/"+Calendar.MONTH+"/"+Calendar.YEAR;
	}
    
	public int getidAfirmacion() {
		return idAfirmacion;
	}

	public void setidAfirmacion(int id) {
		this.idAfirmacion = id;
	}    
    
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getTextoPlano() {
		return textoPlano;
	}

	public void setTextoPlano(String texto) {
		this.textoPlano = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(String fecha) {
		this.fecha_registro = fecha;
	}
	
	public String getFecha_publicacion() {
		return fecha_publicacion;
	}
	
	public void setFecha_publicacion(String fecha) {
		this.fecha_publicacion= fecha;
	}

	public String getVeracidad() {
		return veracidad;
	}

	public void setVeracidad(String veracidad) {
		this.veracidad = veracidad;
	}
	
	public int contarPalabras() {
		return this.textoPlano.split(" ").length;
	}

	@Override
	public String toString() {
		return "Afirmacion [idAfirmacion=" + idAfirmacion + ", autor=" + autor + ", texto=" + texto + ", fecha_registro=" + fecha_registro + ", veracidad="
				+ veracidad + "]";
	}
	
	
}
