package tablonanuncios;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class errorControlador implements ErrorController{

    private static final String PATH = "/error";


    @RequestMapping(value = PATH)
    public String error() {
    	
        return "error al acceder a la pagina";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
