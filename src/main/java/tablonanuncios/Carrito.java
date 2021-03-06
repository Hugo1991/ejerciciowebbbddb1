package tablonanuncios;

import java.util.ArrayList;

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
