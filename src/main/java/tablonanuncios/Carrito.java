package tablonanuncios;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class Carrito {
	private long id;
	private ArrayList<Producto> productos;
	public Carrito(Producto p){
		addProducto(p);
		
	}
	public void addProducto(Producto p){
		productos.add(p);
	}
	public void removeProducto(Producto p){
		productos.remove(productos.equals(p));
	}
	public int CalcularCantidadProducto(Producto p){
		int cont=0;
		for(Producto x:productos){
			if (x.equals(p)){
				cont++;	
			} 
		}
		return cont;
	}
	public void VaciarCesta(){
		for(Producto p:productos)
			this.productos.remove(p);
	}
	public long getId(){
		
		return id;
	}
	public Carrito getCarrito(){
		return this;
	}
	
}
