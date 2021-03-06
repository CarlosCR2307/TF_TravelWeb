package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Restaurante;
import pe.edu.upc.spring.service.IRestauranteService;

@Controller
@RequestMapping("/restaurante")
public class RestauranteController {
	@Autowired
	private IRestauranteService rService;
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	@RequestMapping("/")
	public String irPaginaListadoRestaurantes(Map<String,Object> model) {
		model.put("listaRestaurantes", rService.listar());
		return "listRestaurante";
	}
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("restaurante", new Restaurante());
		return "restaurante";
	}
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Restaurante objRestaurante,BindingResult binRes, Model model,RedirectAttributes objRedir) throws ParseException{
		if(binRes.hasErrors())
			return "restaurante";
		else
		{
			if(objRestaurante.getNombreRestaurante().length()==0 ||objRestaurante.getDireccion().length()==0 ){
				model.addAttribute("mensaje", "Complete todos los campos");
				return "restaurante";
			}
			boolean flag=rService.insertar(objRestaurante);
			if(flag) {
				objRedir.addFlashAttribute("exito", "Se guardo correctamente");
				return "redirect:/restaurante/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/restaurante/irRegistrar";
			}
		}
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id,Model model, RedirectAttributes objRedir) throws ParseException{
		Optional<Restaurante> objRestaurante=rService.listarId(id);
		if(objRestaurante==null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/restaurante/listar";
			
		}
		else {
			model.addAttribute("restaurante", objRestaurante);
			return "restaurante";
		}
	}
	@RequestMapping("/eliminar")
	public String eliminar(Map<String,Object> model,@RequestParam(value="id") Integer id,RedirectAttributes objRedir) {
		try {
			if(id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRestaurantes", rService.listar());
				objRedir.addFlashAttribute("eliminar", "Se elimino correctamente");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaRestaurantes", rService.listar());
		}
		return "listRestaurante";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRestaurantes", rService.listar());
		return "listRestaurante";
	}

}
