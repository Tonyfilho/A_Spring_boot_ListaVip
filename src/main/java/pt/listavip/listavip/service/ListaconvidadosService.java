package pt.listavip.listavip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.listavip.listavip.model.Listaconvidados;
import pt.listavip.listavip.repository.ListaconvidadosRepository;

@Service
public class ListaconvidadosService {
	@Autowired
	ListaconvidadosRepository repository;
	
	
	public List<Listaconvidados> listaAll(){
		List<Listaconvidados> lista = repository.findAll();		
		return lista;
	}
	
	public void salvaTodos(Listaconvidados convidado) {
		Listaconvidados lista = repository.save(convidado);		
		
	}

}
