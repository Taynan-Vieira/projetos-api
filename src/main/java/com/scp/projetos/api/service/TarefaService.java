package com.scp.projetos.api.service;

import com.scp.projetos.api.model.Tarefa;
import com.scp.projetos.api.repository.TarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TarefaService {

	private TarefaRepository tarefaRepository;

	public Tarefa atualizarTarefa(Long id, Tarefa tarefa) {
		Tarefa tarefaSalva = buscaTarefaPorCodigo(id);
		BeanUtils.copyProperties(tarefa, tarefaSalva, "id");
		return salvarTarefa(tarefaSalva);

	}

	public Tarefa salvarTarefa(Tarefa tarefa) {
		if (tarefaRepository.buscarTarefaPorTituloEProjeto(tarefa.getTitulo(), tarefa.getProjeto().getId()).isPresent()) {
			throw new IllegalArgumentException("Tarefa já cadastrada no projeto.");
		}
		tarefa.setTitulo(tarefa.getTitulo().toUpperCase());
		return tarefaRepository.save(tarefa);
	}

	public List<Tarefa> buscaTarefaPorTitulo(String titulo) {
		if (titulo.length() < 3)
			throw new IllegalArgumentException("Título deve ter no mínimo 2 caracteres");

		return tarefaRepository.buscarTarefaPorTitulo(titulo);
	}

	private Tarefa buscaTarefaPorCodigo(Long id) {
		return tarefaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

}
