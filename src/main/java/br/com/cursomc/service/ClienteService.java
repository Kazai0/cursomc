package br.com.cursomc.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Endereco;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.dto.ClienteNewDTO;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.service.exceptions.DataIntegrityException;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		 Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.cursomc.service.exceptions.ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionadas");

		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objtDto) {
		return new Cliente(objtDto.getId(), objtDto.getNome(), objtDto.getEmail(), null, null);
		
	}
	
	public Cliente fromDTO(ClienteNewDTO objtDto) {
		 Cliente cli = new Cliente(null, objtDto.getNome(), objtDto.getEmail(), objtDto.getCpfOuCnpj(), TipoCliente.toEnum(objtDto.getTipo()));
		 Cidade cid = new Cidade(objtDto.getCidadeId(), null, null);
		 Endereco end = new Endereco(null, objtDto.getLogradouro(), objtDto.getNumero(), objtDto.getComplemento(), objtDto.getBairro(), objtDto.getCep(), cli, cid);
		 cli.getEnderecos().add(end);
		 cli.getTelefones().add(objtDto.getTelefone1());
		 if (objtDto.getTelefone2()!= null) {
			 cli.getTelefones().add(objtDto.getTelefone2());
		 }
		 if (objtDto.getTelefone3()!=null) {
			 cli.getTelefones().add(objtDto.getTelefone3());
		 }
		 return cli;
		 
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}
