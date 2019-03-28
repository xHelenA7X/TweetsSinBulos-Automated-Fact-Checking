package ua.model;


public class Administrador {
	private String id;
	private String dni;
    private String nombre;
    private String apellidos;
    private String contrasenya;
	
    public Administrador() {
    	
    }
    
    public Administrador(String id, String dni, String nombre, String apellidos, String contrasenya) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.contrasenya = contrasenya;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", contrasenya=" + contrasenya + "]";
	}
	
}
