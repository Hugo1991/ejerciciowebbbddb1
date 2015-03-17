package tablonanuncios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		 // save a couple of customers
        repository.save(new Producto("TV SAMSUNG", "Televisiones","img/tv.jpg","la mejor del mercaso",300.00));
        repository.save(new Producto("TV SAMSUNG", "Radio","URLimage","la mejor del mercaso",300.00));
        repository.save(new Producto("TV SAMSUNG", "Aspiradora","URLimage","la mejor del mercaso",300.00));
        repository.save(new Producto("TV SAMSUNG", "Televisiones","URLimage","la mejor del mercaso",300.00));
        
        // fetch all customers
        Iterable<Producto> productos = repository.findAll();
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
        System.out.println();

        // fetch an individual customer by ID
        Producto producto = repository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(producto);
        System.out.println();

        
        
	}
}
