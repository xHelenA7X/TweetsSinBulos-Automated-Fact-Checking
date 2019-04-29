package ua.model;

public class FuenteExterna {
	private String fuente;
	private String[] titulo;
	private String[] link;
	private String[] cuerpo;
	
	public FuenteExterna() {
		fuente = "";
		setTitulo(new String[2]);
		setLink(new String[2]);
		setCuerpo(new String[2]);
	}
	
	
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}


	public String[] getTitulo() {
		return titulo;
	}


	public void setTitulo(String[] titulo) {
		this.titulo = titulo;
	}


	public String[] getLink() {
		return link;
	}


	public void setLink(String[] link) {
		this.link = link;
	}


	public String[] getCuerpo() {
		return cuerpo;
	}


	public void setCuerpo(String[] cuerpo) {
		this.cuerpo = cuerpo;
	}	
}
