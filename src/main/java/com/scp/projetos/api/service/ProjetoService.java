package com.scp.projetos.api.service;

import com.scp.projetos.api.model.Projeto;
import com.scp.projetos.api.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjetoService {

	private ProjetoRepository projetoRepository;

	public Projeto atualizarProjeto(Long id, Projeto projeto) {
		Projeto projetoSalvo = buscaProjetoPorCodigo(id);
		BeanUtils.copyProperties(projeto, projetoSalvo, "id");
		return salvarProjeto(projetoSalvo);

	}

	private Projeto buscaProjetoPorCodigo(Long id) {
		return projetoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Projeto salvarProjeto(Projeto projeto) {
		if (projetoRepository.buscarProjetoPorTitulo(projeto.getTitulo()).isPresent()) {
			throw new IllegalArgumentException("Projeto já cadastrado.");
		}
		projeto.setTitulo(projeto.getTitulo().toUpperCase());
		return projetoRepository.save(projeto);
	}
}
