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
	private Usuario usuario=new Usuario();
	@RequestMapping("/")
	public ModelAndView index(HttpSession sesion) {
		ModelAndView mv = new ModelAndView("index").addObject("productos",repository.findAll()).addObject("carrito",carrito);

		if (sesion.isNew()) {
			mv.addObject("saludo", "Bienvenido!!");
			sesion.setAttribute("usuario", usuario);
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
	@RequestMapping(value="/indexPrice",method=RequestMethod.GET)
	public ModelAndView indexPrice(Double precioMin,Double precioMax) {
		ArrayList<Producto> productos=new ArrayList<Producto>();
		for(Producto p:repository.findAll())
			if ((p.getPrecio()>=precioMin) && (p.getPrecio()<=precioMax))
				productos.add(p);
		//añadiendo las categorias
		ModelAndView mv = new ModelAndView("index").addObject("productos",productos).addObject("carrito", carrito);
		return mv;
	}


	//METDOS DE PRODUCTOS
	@RequestMapping("/mostrarProducto")
	public ModelAndView mostrar(@RequestParam long idProducto) {
		Producto producto = repository.findOne(idProducto);
		return new ModelAndView("producto").addObject("producto", producto).addObject("carrito",carrito);
	}

	
	//METODOS DE CESTA DE LA COMPRA
	@RequestMapping(value="/addCarrito",method=RequestMethod.POST)
	public ModelAndView anadirCarrito(@RequestParam Long idProducto,int cantidad){
		if (idProducto!=null){
			producto = repository.findOne(idProducto);
			carrito.addProducto(producto,cantidad);
			return new ModelAndView("carrito").addObject("producto", producto).addObject("carrito",carrito); 
		}
		return new ModelAndView("/");
	}
	@RequestMapping("/mostrarCarrito")
	public ModelAndView mostrarCarrito(){
			return new ModelAndView("carrito").addObject("producto", producto).addObject("carrito",carrito);
	}
	@RequestMapping(value="/eliminarCarrito",method=RequestMethod.POST)
	public ModelAndView eliminarCarrito(Long idProducto){
		producto = repository.findOne(idProducto);		
		carrito.removeProducto(producto);
		return new ModelAndView("carrito").addObject("producto",producto).addObject(carrito); 
		
	}
	
	//METODOS DE PEDIDO
	@RequestMapping(value="/crearPedido",method=RequestMethod.POST)
	public ModelAndView crearPedido(){
		Pedido pedido=new Pedido();
		pedido.setCarrito(carrito);
		return new ModelAndView("formularioCompra").addObject("pedido", pedido).addObject("carrito",carrito);
		
	}
	@RequestMapping(value ="/confirmarPedido", method=RequestMethod.POST)
	public ModelAndView confirmarPedido(@ModelAttribute("pedido") Pedido pedido, @RequestParam String nombre1, @RequestParam String apellidos){
		Usuario usuario =new Usuario(nombre1,apellidos);
		pedido.setUsuario(usuario);
		pedidos.add(pedido);
		System.out.println("ahora se procedera a borrar el carrito");
		//carrito.VaciarCesta();
		return new ModelAndView("/").addObject("productos",repository.findAll()).addObject(carrito);
		
	}
	//Pagina de administracion
	@RequestMapping("/sesion")
	public ModelAndView sesion(HttpSession sesion){
		Administrador admin=new Administrador();
		return new ModelAndView("sesion").addObject("admin", admin).addObject("carrito",carrito);
	}
	@RequestMapping(value="admin",method=RequestMethod.POST)
	public ModelAndView logIn(@ModelAttribute("admin") Administrador admin, @RequestParam String nombre, @RequestParam String pass){
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
	public ModelAndView mostrarPedido(@ModelAttribute("admin") Administrador admin, @RequestParam String nombre, @RequestParam String pass){
		ModelAndView mv=new ModelAndView("administracion").addObject("productos",repository.findAll());//.addObject("pedidos",repositorio.findAll());
		
		if (nombre.equalsIgnoreCase(admin.getNombre()) && pass.equalsIgnoreCase(admin.getPass()))
			return mv;
		else
			return new ModelAndView("loginError");
	}
	@RequestMapping(value="/addProducto",method=RequestMethod.POST)
	public ModelAndView insertar(@RequestParam String nombre,@RequestParam String categoria,@RequestParam Double precio,@RequestParam String descripcion,@RequestParam("imagen") MultipartFile imagen) {
		String fileName = imagen.getOriginalFilename();
		File uploadedFile=new File("");
		if (!imagen.isEmpty()) {
			   try {
				   File filesFolder = new File("src/main/resources/static/img/");
				   if (!filesFolder.exists()) {
					   System.out.println("carpeta no existe");
				   }
				   
				    uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				   imagen.transferTo(uploadedFile);
			   }catch(Exception e){
			   }
		}else{
			
			
			System.out.println("archivo vacio");
		}
		repository.save(new Producto(nombre, categoria,precio,descripcion,"img/"+fileName));
		return new ModelAndView("administracion").addObject("productos",repository.findAll()).addObject("carrito",carrito);
	}
	@RequestMapping(value="/borrarProducto",method=RequestMethod.POST)
	public ModelAndView borrar(@RequestParam Long idProducto, HttpSession sesion) {
		repository.delete(idProducto);
		return new ModelAndView("administracion").addObject("productos",repository.findAll()).addObject("carrito",carrito);
	}
	@RequestMapping(value="/modificarPrducto",method=RequestMethod.POST)
	public ModelAndView modificarProducto(@RequestParam Long idProducto, HttpSession sesion) {
		
		
		return new ModelAndView("administracion").addObject("productos",repository.findAll()).addObject("carrito",carrito);
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