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
	

	@RequestMapping("/")
	public ModelAndView tablon(HttpSession sesion) {

		ModelAndView mv = new ModelAndView("index").addObject("productos",
				repository.findAll());

		if (sesion.isNew()) {
			mv.addObject("saludo", "Bienvenido!!");
		}

		return mv;
	}
	@RequestMapping("/")
	public ModelAndView tablon(HttpSession sesion, String cat) {
		ArrayList<Producto> p=(ArrayList<Producto>) repository.findAll();
		p.get(0).getCategoria();
		//añadiendo las categorias
		ModelAndView mv = new ModelAndView("index").addObject("productos",
				repository.findAll());

		if (sesion.isNew()) {
			mv.addObject("saludo", "Bienvenido!!");
		}

		return mv;
	}
	@RequestMapping("/insertar")
	public ModelAndView insertar(Producto producto, HttpSession sesion) {
		repository.save(producto);
		sesion.setAttribute("nombre", producto.getNombre());
		return new ModelAndView("insertar");
	}

	@RequestMapping("/producto")
	public ModelAndView mostrar(@RequestParam long idProducto) {
		Producto producto = repository.findOne(idProducto);
		return new ModelAndView("producto").addObject("producto", producto);
	}
	
	@RequestMapping("/carrito")
	public ModelAndView anadirCarrito(HttpSession sesion,Producto p){
		return new ModelAndView("carrito"); 
	}
	@RequestMapping("/nuevoAnuncio")
	public ModelAndView nuevoAnuncio(HttpSession sesion) {

		String nombre = (String) sesion.getAttribute("nombre");
		
		return new ModelAndView("nuevoAnuncio").addObject("nombre", nombre);
	}
	
		
	
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