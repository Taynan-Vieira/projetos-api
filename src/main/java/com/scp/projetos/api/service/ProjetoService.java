package com.scp.projetos.api.service;

import com.scp.projetos.api.model.Projeto;
import com.scp.projetos.api.repository.ProjetoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjetoService {

	@Autowired
	private transient ProjetoRepository projetoRepository;

	public Projeto atualizarProjeto(Long id, Projeto projeto) {
		Projeto projetoSalvo = buscaProjetoPorCodigo(id);
		BeanUtils.copyProperties(projeto, projetoSalvo, "id");
		return projetoRepository.save(projetoSalvo);

	}

	private Projeto buscaProjetoPorCodigo(Long id) {
		Optional<Projeto> projetoSalvo = Optional.ofNullable(projetoRepository.findById(id))
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return projetoSalvo.get();
	}

	public Projeto salvarProjeto(Projeto projeto){
		if(!projetoRepository.buscarProjetoPorTitulo(projeto.getTitulo()).isPresent()){
			projeto.setTitulo(projeto.getTitulo().toUpperCase());
			return projetoRepository.save(projeto);
		}
		throw new IllegalArgumentException("Projeto já cadastrado.");
	}
}
