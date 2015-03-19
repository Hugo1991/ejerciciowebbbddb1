package tablonanuncios;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class Carrito {
	private long id;
	private ArrayList<Producto> productos=new ArrayList<Producto>();
	private Double precioFinal=0.0;

	public void addProducto(Producto p){
		productos.add(p);
		CalcularPrecioFinal();
	}
	public void removeProducto(Producto p){
		for(Producto pr:productos){
			if(pr.getId()==(p.getId())){
				productos.remove(pr);
				break;
			}
			}
		CalcularPrecioFinal();
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
	private void CalcularPrecioFinal(){
		precioFinal=0.0;
		for(Producto x:productos){
			precioFinal+=x.getPrecio();
		}
	}
	public void VaciarCesta(){
		for(Producto p:productos)
			this.productos.remove(p);
	}
	public int contador(Producto p){
		int conta=0;
		for(Producto pr:productos){
			if (pr.equals(p)){
				conta+=1;
			}
		}
		return conta;
	}
	public long getId(){
		return id;
	}
	public ArrayList<Producto> getCarrito(){
		return productos;
	}
	public double getPrecioFinal(){
		return precioFinal;
	}
	
}
