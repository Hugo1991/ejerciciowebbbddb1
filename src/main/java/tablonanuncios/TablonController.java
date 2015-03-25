package tablonanuncios;

import javax.servlet.http.HttpServletResponse;

import java.io.File;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TablonController {
	private static final String FILES_FOLDER = "img";
	private List<String> imageTitles = new ArrayList<>();
	
	@Autowired
	private ProductRepository repository;
	private ArrayList<Pedido> pedidos=new ArrayList<Pedido>();
	private Carrito carrito=new Carrito("as");
	private Producto producto=new Producto();
	
	@RequestMapping("/")
	public ModelAndView index(HttpSession sesion) {
		ModelAndView mv = new ModelAndView("index").addObject("productos",
				repository.findAll()).addObject("carrito",carrito);

		if (sesion.isNew()) {
			mv.addObject("saludo", "Bienvenido!!");
			 
			carrito=new Carrito("as");
			//sesion.setAttribute("carrito", carrito.getId());
		}
		return mv;
	}
	@RequestMapping("/index")
	public ModelAndView indexName(HttpSession sesion, String find) {
		ArrayList<Producto> productos=new ArrayList<Producto>();
		for(Producto p:repository.findAll())
			if (p.getCategoria().contains(find) || p.getNombre().contains(find)||p.getDescripcion().contains(find))
				productos.add(p);
		//añadiendo las categorias
		ModelAndView mv = new ModelAndView("index").addObject("productos",productos).addObject("categorias",find).addObject("carrito", carrito);
		if (sesion.isNew()) {
			mv.addObject("saludo", "Bienvenido!!");
		}
		return mv;
	}
	@RequestMapping("/indexPrice")
	public ModelAndView indexPrice(HttpSession sesion, String find) {
		ArrayList<Producto> productos=new ArrayList<Producto>();
		for(Producto p:repository.findAll())
			if (p.getCategoria().contains(find) || p.getNombre().contains(find)||p.getDescripcion().contains(find))
				productos.add(p);
		//añadiendo las categorias
		ModelAndView mv = new ModelAndView("index").addObject("productos",productos).addObject("categorias",find).addObject("carrito", carrito);
		if (sesion.isNew()) {
			mv.addObject("saludo", "Bienvenido!!");
		}
		return mv;
	}


	//METDOS DE PRODUCTOS
	@RequestMapping("/mostrarProducto")
	public ModelAndView mostrar(HttpSession sesion,@RequestParam long idProducto) {
		Producto producto = repository.findOne(idProducto);
		return new ModelAndView("producto").addObject("producto", producto).addObject("carrito",carrito);
	}
	@RequestMapping(value="/addProducto",method=RequestMethod.POST)
	public ModelAndView insertar(HttpSession sesion,@RequestParam String nombre,@RequestParam String categoria,@RequestParam String descripcion, @RequestParam Double precio) {
		repository.save(new Producto(nombre, categoria,"img/",descripcion,precio));
		return new ModelAndView("administracion").addObject("productos",repository.findAll()).addObject("carrito",carrito);
	}
	@RequestMapping(value="/borrarProducto",method=RequestMethod.POST)
	public ModelAndView borrar(@RequestParam Long idProducto, HttpSession sesion) {
		repository.delete(idProducto);
		return new ModelAndView("administracion").addObject("productos",repository.findAll()).addObject("carrito",carrito);
	}
	
	//METODOS DE CESTA DE LA COMPRA
	@RequestMapping(value="/addCarrito",method=RequestMethod.POST)
	public ModelAndView anadirCarrito(HttpSession sesion,@RequestParam Long idProducto,int cantidad){
		if (idProducto!=null){
			producto = repository.findOne(idProducto);
			carrito.addProducto(producto,cantidad);
			return new ModelAndView("carrito").addObject("producto", producto).addObject("carrito",carrito); 
		}
		return new ModelAndView("/");
	}
	@RequestMapping("/mostrarCarrito")
	public ModelAndView mostrarCarrito(HttpSession sesion){
			return new ModelAndView("carrito").addObject("producto", producto).addObject("carrito",carrito);
	}
	@RequestMapping(value="/eliminarCarrito",method=RequestMethod.POST)
	public ModelAndView eliminarCarrito(HttpSession sesion, Long idProducto){
		producto = repository.findOne(idProducto);		
		carrito.removeProducto(producto);
		return new ModelAndView("carrito").addObject("producto",producto).addObject(carrito); 
		
	}
	
	//METODOS DE PEDIDO
	@RequestMapping(value="/crearPedido",method=RequestMethod.POST)
	public ModelAndView crearPedido(HttpSession sesion){
		Pedido pedido=new Pedido();
		pedido.setCarrito(carrito);
		return new ModelAndView("formularioCompra").addObject("pedido", pedido).addObject("carrito",carrito);
		
	}
	@RequestMapping(value ="/confirmarPedido", method=RequestMethod.POST)
	public ModelAndView confirmarPedido(HttpSession sesion,@ModelAttribute("pedido") Pedido pedido, @RequestParam String nombre1, @RequestParam String apellidos){
		Usuario usuario =new Usuario(nombre1,apellidos);
		pedido.setUsuario(usuario);
		pedidos.add(pedido);
		System.out.println("ahora se procedera a borrar el carrito");
		//carrito.VaciarCesta();
		return new ModelAndView("/").addObject("productos",repository.findAll()).addObject(carrito);
		
	}
	//Pagina de administracion
	@RequestMapping("/sesion")
	public ModelAndView sesion(){
		Administrador admin=new Administrador();
		return new ModelAndView("sesion").addObject("admin", admin).addObject("carrito",carrito);
	}
	@RequestMapping(value="admin",method=RequestMethod.POST)
	public ModelAndView logIn(HttpSession sesion, @ModelAttribute("admin") Administrador admin, @RequestParam String nombre, @RequestParam String pass){
		if ((nombre==null)||(pass==null))
			return new ModelAndView("loginError").addObject(carrito);
		else{
			ModelAndView mv=new ModelAndView("administracion").addObject("productos",repository.findAll()).addObject("carrito", carrito).addObject("pedidos",pedidos);
			if (nombre.equalsIgnoreCase(admin.getNombre()) && pass.equalsIgnoreCase(admin.getPass()))
				return mv;
			else
				return new ModelAndView("sesion").addObject("carrito", carrito);
		}
	}
	@RequestMapping("/mostrarPedido")
	public ModelAndView mostrarPedido(HttpSession sesion, @ModelAttribute("admin") Administrador admin, @RequestParam String nombre, @RequestParam String pass){
		ModelAndView mv=new ModelAndView("administracion").addObject("productos",repository.findAll());//.addObject("pedidos",repositorio.findAll());
		
		if (nombre.equalsIgnoreCase(admin.getNombre()) && pass.equalsIgnoreCase(admin.getPass()))
			return mv;
		else
			return new ModelAndView("loginError");
	}
	/*@RequestMapping("/pedidos")
	public ModelAndView logIn(HttpSession sesion, @ModelAttribute("admin") Administrador admin, @RequestParam String nombre, @RequestParam String pass){
		ModelAndView mv=new ModelAndView("administracion").addObject("productos",repository.findAll());//.addObject("pedidos",repositorio.findAll());
		
		if (nombre.equalsIgnoreCase(admin.getNombre()) && pass.equalsIgnoreCase(admin.getPass()))
			return mv;
		else
			return new ModelAndView("loginError");
	}*/
	@RequestMapping("/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File(FILES_FOLDER, fileName+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils
					.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}
}