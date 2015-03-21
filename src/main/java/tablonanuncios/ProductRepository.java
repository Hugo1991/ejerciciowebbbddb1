package tablonanuncios;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Producto, Long> {

	//Aquí van las consultas a la BBDD
	/*ArrayList<Producto> BuscaNombre(String nombre);
	ArrayList<Producto> BuscaNombreCategoria(String categoria);
	ArrayList<Producto> BuscaPrecio(Double p1,Double p2);
*/
}