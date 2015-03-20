package tablonanuncios;

public class Pedido {
	private Carrito carrito;
	private Usuario usuario;
	public Pedido(){}
	public Pedido(Usuario usuario,Carrito carrito){}
	
	public void setUsuario(Usuario usuario){
		this.usuario=usuario;
	}
	public void setCarrito(Carrito carrito){
		this.carrito=carrito;
	}
	public Usuario getUsuario(){
		return usuario;
	}
	public Carrito getCarrito(){
		return carrito;
	}
}
