package ua.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Afirmacion {
	private String autor;
    private String texto;
    private String fecha_registro;
    private String veracidad;
    private int idAfirmacion;
    
    public Afirmacion() {
    	
    }
    
    public Afirmacion(String autor, String texto, String veracidad) {
		this.autor = autor;
		this.texto = texto;
		Calendar fecha = new GregorianCalendar();
		this.fecha_registro = Calendar.DAY_OF_MONTH+"/"+Calendar.MONTH+"/"+Calendar.YEAR;
		this.veracidad = veracidad;
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

	public String getVeracidad() {
		return veracidad;
	}

	public void setVeracidad(String veracidad) {
		this.veracidad = veracidad;
	}

	@Override
	public String toString() {
		return "Afirmacion [idAfirmacion=" + idAfirmacion + ", autor=" + autor + ", texto=" + texto + ", fecha_registro=" + fecha_registro + ", veracidad="
				+ veracidad + "]";
	}
	
	
}
