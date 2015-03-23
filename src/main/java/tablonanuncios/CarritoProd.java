package tablonanuncios;

public class CarritoProd {
	private Producto producto;
	private int cantidad=0;
	public CarritoProd(Producto p){
		setProducto(p);
	}
	public void setProducto(Producto p){
		producto=p;
		cantidad=1;
	}
	public boolean existe(Producto p){
		
		return this.getProducto()==p;
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
