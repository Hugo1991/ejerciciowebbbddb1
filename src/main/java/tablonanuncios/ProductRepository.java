package tablonanuncios;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Producto, Long> {

	//Aquí van las consultas a la BBDD
	
}