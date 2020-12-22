package br.com.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Pedido;
import br.com.cursomc.repositories.PedidoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.cursomc.service.exceptions.ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		} 

	
}
