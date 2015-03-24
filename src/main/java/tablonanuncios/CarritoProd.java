package tablonanuncios;

public class CarritoProd {
	private Producto producto;
	private int cantidad=0;
	public CarritoProd(){
		cantidad=1;
	}
	public void setProducto(Producto p){
		producto=p;
		
	}
	public boolean existe(Producto p){
		return this.getProducto().getId()==p.getId();
	}
	public void sumaCantidad(){
		cantidad+=1;
		
	}
	public int getCantidad(){
		
		return cantidad;
	}
	public Producto getProducto(){
		return producto;
	}
}
