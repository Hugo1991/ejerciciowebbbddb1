package tablonanuncios;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Carrito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private ArrayList<CarritoProd> productos=new ArrayList<CarritoProd>();
	private Double precioFinal=0.0;
	
	public Carrito(){}
	public Carrito(String l){
		String u;
		u=l;
	}
	
	public void addProducto(Producto p,int cantidad){
		boolean encontrado=false;
		 CarritoProd pi;

		if (productos.isEmpty()){
			System.out.println("carrito vacio"+p.getId());
			CarritoProd cp=new CarritoProd(p);
			productos.add(cp);
			encontrado=true;
		}else{
			Iterator<CarritoProd> iterador= productos.iterator();
			while(!encontrado&&iterador.hasNext()){
				pi = iterador.next();
				if (pi.existe(p)){
					System.out.println("producto igual");
					pi.setCantidad(cantidad);
					encontrado=true;
				}
				
			}
		}
		if (!encontrado){
			CarritoProd cp=new CarritoProd(p);
			productos.add(cp);
			System.out.println("producto diferente,añadido"+p.getId());
		}
		calcularPrecioTotal();

	}
	public void removeProducto(Producto p){
		for(CarritoProd pr:productos){
			if(pr.getProducto().getId()==(p.getId())){
				if (pr.getCantidad()>=2)
					pr.restaCantidad();
				else
					productos.remove(pr);
				break;
					
				}
			}
			
		calcularPrecioTotal();
	}
	/*public int CalcularCantidadProducto(Producto p){
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
			precioFinal+=x.getPrecio()*cantidad;
		}
	}
	public void VaciarCesta(){
		try{
		for(Producto p:productos){
			removeProducto(p);
			System.out.println(p+" borrado");
		}
		}catch(Exception e){
			System.out.println("error borrando producto");
		}
		finally{
			productos=new ArrayList<Producto>();
			precioFinal=0.0;
		}
	}*/
	public int getNumeroProductos(){
		int cantidad=0;

		for (CarritoProd p: productos){
			cantidad+=p.getCantidad();
		}
		return cantidad;
	}
	/*
	public int contador(Producto p){
		int conta=0;
		for(Producto pr:productos){
			if (pr.equals(p)){
				conta+=1;
				
			}
		}
		return conta;
	}*/
	public long getId(){
		return id;
	}
	public ArrayList<CarritoProd> getCarrito(){
		return productos;
	}
	
	private void calcularPrecioTotal(){
		precioFinal=0.0;
		for(CarritoProd p:productos){
			precioFinal+=p.getCantidad()*p.getProducto().getPrecio();
		}
	}
	public double getPrecioFinal(){
		return precioFinal;
	}
	
}
