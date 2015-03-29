package tablonanuncios;

public class Pedido {
	private Carrito carrito;
	private String usuario;
	public Pedido(){}
	public Pedido(Usuario usuario,Carrito carrito){}
	
	public void setUsuario(String usuario){
		this.usuario=usuario;
	}
	public void setCarrito(Carrito carrito){
		this.carrito=carrito;
	}
	
	public String getUsuario(){
		return usuario;
	}
	public Carrito getCarrito(){
		return carrito;
	}
}
