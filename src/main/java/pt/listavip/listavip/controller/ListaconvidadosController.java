package pt.listavip.listavip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pt.listavip.email.enviadorEmail.Enviador;
import pt.listavip.listavip.model.Listaconvidados;

import pt.listavip.listavip.service.ListaconvidadosService;

@Controller
public class ListaconvidadosController {
	
	@Autowired
	ListaconvidadosService listaService;
	

	@RequestMapping(value = "/")
//	@ResponseBody
	public String Index() {
		
		return "/index";		
	}
	@RequestMapping(value = "/listaconvidados")
	public String ListaConvidados(Model model) {
		List<Listaconvidados>lista = listaService.listaAll();
		model.addAttribute("convidados", lista);
		return "listaconvidados";
	}// fim da lista
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST  )
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email
			,@RequestParam("telefone") String telefone, Model model) {/*recebendo os paramentros do FORM lá do HTML
			com o mesmo valor no NAME, passaremos o Construtor para abastecer nossa entity*/
			Listaconvidados listaconvidados = new Listaconvidados(nome,email,telefone);
			listaService.salvaTodos(listaconvidados);
			new Enviador().enviarEmail(nome, email);
			/*para retorna para pagina principal temos que novamente fazer a busca no banco 
			 * com findAll, Vamos por o MODEL como paramentro do nosso metodo*/
			List<Listaconvidados>salvaNovo = listaService.listaAll();
			model.addAttribute("convidados", salvaNovo);
//			Enviador enviador = new Enviador();
//			enviador.enviarEmail(nome, email); 
		
		return "listaconvidados";
	}
	
	
}// fim da class
