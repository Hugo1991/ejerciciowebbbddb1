package tablonanuncios;

public class Usuario {
	private String nombre;
	private String apellidos;
	public Usuario(){}
	public Usuario(String nombre, String apellidos){
		setNombre(nombre);
		setApellidos(apellidos);
		
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
		
	}
	public void setApellidos(String apellidos){
		this.apellidos=apellidos;
		
	}
	public String getNombre(){
		return nombre;
	}
	public String getApellidos(){
		return apellidos;
	}
}
