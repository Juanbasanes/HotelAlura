package jdbc.modelo;

public class Administradores {

	private Integer id;
	private String nombre;
	private String contrasena;
	
	public Administradores(String nombre, String contrasena) {
		this.nombre= nombre;
		this.contrasena = contrasena;
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contrasena;
	}
	public void setContraseña(String contraseña) {
		this.contrasena = contraseña;
	}
	
	@Override
	public String toString() {
		return String.format("{id:%s, nombre:%s, contraseña:%s}", 
				this.id,
				this.nombre,
				this.contrasena);			
	}
}
