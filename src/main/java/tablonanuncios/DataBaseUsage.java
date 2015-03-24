package tablonanuncios;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private ProductRepository repository;
	private CarritoRepository repositorye;
	//private PedidosRepository repositorioPedidos;
	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

        //PEQUEÑOS ELECTRODOMESTICOS
        repository.save(new Producto("Depiladora - Rio Beauty LAH-R Depilación láser", "Pequeños electrodomesticos","img/DepLaser.jpg","Ahora podrás disfrutar de los beneficios de la depilación láser, sin tener que pagar costosos tratamientos",94.90));
        repository.save(new Producto("Cortapelos - Philips QC5580/32, REcargable, 14", "Pequeños electrodomesticos","img/Cortapelos.jpg","nuevo producto QC5580/12 con el que podrás cortarte el pelo de manera muy fácil y conseguirá unos resultados inmejorables",60.90));
        repository.save(new Producto("Rizador - Babyliss Curl Secret C1000E Cámara cerámica,", "Pequeños electrodomesticos","img/Rizador.png"," Babyliss el primer rizador de cabello totalmente automático, el rizador que has estado esperando",89d));
        repository.save(new Producto("Palomitero - Sogo PAL-SS 1447 Diseño anticuario, 850W, Pie anti-deslizante", "Pequeños electrodomesticos","img/palomitero.jpg","lo que te faltaría para poder tener  una tarde perfecta, tu palomitero PAL-SS 1447 de Sogo",25d));
        
        //INFORMATICA
        repository.save(new Producto("eBook - SPC Dickens XL 5900N 9 pulgadas a color, 4GB y vídeo HD", "Informatica","img/eBook.PNG","¡Tu música, pelis, fotos y libros conviven de maravilla en el eBook SPC Internet Dickens XL 5900N!",99.00));
        repository.save(new Producto("2 en 1 convertible - Asus Tansformer Pad TF103C Negra, 16GB e Intel® HD Graphics", "Informatica","img/Tablet.jpg","soy el 2 en 1 convertible Asus Transformer Pad (TF103C-1B033A) mido 10.1 y dicen que soy un 2x1",232d));
        repository.save(new Producto("PC Sobremesa - Acer Aspire ATC-605 i7-4790, NVIA GTX745 de 4GB y 2TB", "Informatica","img/PC.jpg","Ordenador de sobremesa con componentes de excelente calidad, un amplísimo disco duro de 2TB, WiFi, Bluetooth, grabadora DVD, teclado y ratón USB",899d));
        repository.save(new Producto("Portátil - HP Pavilion 15-P139NS, i5-4288U, 1TB y BeatsAudio™", "Informatica","img/portatil.jpg","Porque tiene un procesador Intel® Core™ i5-4288U y 4GB de RAM que trabajan constantemente para ofrecerte el mejor rendimiento del PC.",599d));
        
        //TELEVISIONES
        repository.save(new Producto("TV OLED 55 - LG 55EC930V Smart TV WebOS, 3D, diseño curvo", "Televisiones","img/tv55.jpg","Lo más parecido a una pantalla de cine, es el televisor OLED de diseño curvo 55EC930V de LG. Con una pantalla de 55 pulgadas y resolución Full HD.",2795d));
        repository.save(new Producto("TV LED 40 - Samsung 40H5570 Smart TV, Full HD, 100Hz ", "Televisiones","img/tv40.png","Con el 40H5570 de Samsung podrás utilizarlo para todo lo que quieras. El Smart TV integrado en este televisor de 40 pulgadas incluye un procesador Quad Core",399d));
        repository.save(new Producto("TV LED 84 - LG 84UB980V Ultra HD 4K, Smart TV, 3D, 1300Hz MCI, Panel IPS ", "Televisiones","img/tv84.jpg","Cierra los ojos e imagina el televisor perfecto para los que lo quieren todo. Un diseño espectacular, las mejores tecnologías de imagen.",8599d));
        repository.save(new Producto("TV LED 49 - Philips 49PUS7909/12, Smart TV Android™, 4K, 3D ", "Televisiones","img/tv49.jpg","Philips aúna sus fuerzas con la tecnología Android para crear el primer televisor con infinidad de aplicaciones, películas, música, redes sociales, juegos",960d));
        
        //VIDEOCONSOLAS
        repository.save(new Producto("Sony - PS4 Negra Básica, 500Gb", "Videoconsolas","img/ps4.png","¡Más que una consola! La versatilidad que te ofrece PS4 es inmejorable, superando la concepción de que las consolas son simplemente para jugar. Juega, escucha música, disfruta de películas, series de TV",378.9));
        repository.save(new Producto("New 3DS XL Azul Metálico - Nintendo ", "Videoconsolas","img/3ds.jpg","La next-gen de consolas portátiles de Nintendo aterriza con su nueva New Nintendo 3DS XL",191d));
        repository.save(new Producto("Microsoft - Xbox 360 250Gb, Negra", "Videoconsolas","img/xbox.jpg","La Xbox 360 dispone del mejor entretenimiento en juegos. Además, podrás aprovecharla para ver películas con su lector de DVD",239d));
        repository.save(new Producto("Wii U Premium, 32Gb + Mario Kart 8 + New Super Mario Bros U", "Videoconsolas","img/WiiU.jpg","Wii U es una consola que ofrece diversión para todos lo públicos, juega con tu familia, con tus amigos y con quien tu quieras.",314d));
        
        repositorye.save(new Carrito(""));
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
