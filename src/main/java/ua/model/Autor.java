package ua.model;


public class Autor {
	private String nombrePerfil;
	private String idAutor;
    private String alias;
    private String descripcion;
    private String localizacion;
    private String cuentaVerificada;
    private String temaPorDefecto;
    private String imagenPorDefecto;
    private String antesBulo;

	public Autor(String idAutor, String nombrePerfil, String alias, String descripcion, String localizacion,
			String cuentaVerificada, String temaPorDefecto, String imagenPorDefecto) {
		this.nombrePerfil = nombrePerfil;
		this.idAutor = idAutor;
		this.alias = alias;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.cuentaVerificada = cuentaVerificada;
		this.temaPorDefecto = temaPorDefecto;
		this.imagenPorDefecto = imagenPorDefecto;
		this.antesBulo = "false";
	}
	
	public Autor() {
		
	}
    
	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public String getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(String idAutor) {
		this.idAutor = idAutor;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getCuentaVerificada() {
		return cuentaVerificada;
	}

	public void setCuentaVerificada(String cuentaVerificada) {
		this.cuentaVerificada = cuentaVerificada;
	}

	public String getTemaPorDefecto() {
		return temaPorDefecto;
	}

	public void setTemaPorDefecto(String temaPorDefecto) {
		this.temaPorDefecto = temaPorDefecto;
	}

	public String getImagenPorDefecto() {
		return imagenPorDefecto;
	}

	public void setImagenPorDefecto(String imagenPorDefecto) {
		this.imagenPorDefecto = imagenPorDefecto;
	}

	public String getAntesBulo() {
		return antesBulo;
	}

	public void setAntesBulo(String antesBulo) {
		this.antesBulo = antesBulo;
	}

	@Override
	public String toString() {
		return "Autor [nombrePerfil=" + nombrePerfil + ", idAutor=" + idAutor + ", alias=" + alias + ", descripcion="
				+ descripcion + ", localizacion=" + localizacion + ", cuentaVerificada=" + cuentaVerificada
				+ ", temaPorDefecto=" + temaPorDefecto + ", imagenPorDefecto=" + imagenPorDefecto + ", antesBulo="
				+ antesBulo + "]";
	}
    
	
}
