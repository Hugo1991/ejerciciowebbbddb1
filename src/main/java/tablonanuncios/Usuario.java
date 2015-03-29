package tablonanuncios;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Usuario {
	private String nombre;
	private String apellidos;
	private Carrito carro = new Carrito();
	private Producto prod;
	private Pedido pedido;

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
	
	
	public Carrito getCarro(){
		return carro;
	}

	public void setProductoCarrito(Producto p,int cantidad) {
		this.carro.addProducto(p,cantidad);
		
	}
	public void setPedido(Pedido pedido){
		this.pedido=pedido;
	}
	public Pedido getPedido(){
		return pedido;
	}
}
