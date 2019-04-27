package ua.model;

public class Palabra {
	private String palabra;
	private String firmeza;
	
	public Palabra() {
		palabra="";
		firmeza="";
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getFirmeza() {
		return firmeza;
	}

	public void setFirmeza(String firmeza) {
		this.firmeza = firmeza;
	}
	
}
