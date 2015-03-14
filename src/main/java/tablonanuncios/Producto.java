package tablonanuncios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String categoria;
	private String imagen;
	private String descripcion;
	private Double precio;

	protected Producto() {
		// Used by SpringData
	}

	public Producto(String nombre, String categoria,String imagen,String descripcion,Double precio) {
		setNombre(nombre);
		setCategoria(categoria);
		setImagen(imagen);
		setDescription(descripcion);
		setPrecio(precio);
	}

	public String getNombre() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}
	public String getImagen() {
		return imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public Double getPrecio() {
		return precio;
	}

	

	@Override
	public String toString() {
		return String.format("Producto[id=%d, Name='%s', Categorie='%s',Image='%s',"
				+ " Description='%s', Prize='%s']",
				id, nombre, categoria,imagen,descripcion,precio);
	}


	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public void setDescription(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPrecio(Double precio) {
		this.precio =precio;
	}
	
	public long getId() {
		return id;
	}

}
