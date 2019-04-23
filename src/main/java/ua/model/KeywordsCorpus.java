package ua.model;

public class KeywordsCorpus {
	private String palabra;
	private int fila;
	private int numeroApariciones;
	
	public KeywordsCorpus(String palabra, int fila, int apariciones) {
		this.palabra = palabra;
		this.fila = fila;
		this.numeroApariciones = apariciones;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getNumeroApariciones() {
		return numeroApariciones;
	}

	public void setNumeroApariciones(int numeroApariciones) {
		this.numeroApariciones = numeroApariciones;
	}
}
