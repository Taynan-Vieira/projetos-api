package com.scp.projetos.api.service;

import com.scp.projetos.api.model.Tarefa;
import com.scp.projetos.api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

	@Autowired
	private transient TarefaRepository tarefaRepository;

	public Tarefa salvarTarefa(Tarefa tarefa){
		if(!tarefaRepository.buscarTarefaPorTitulo(tarefa.getTitulo(), tarefa.getProjeto().getId()).isPresent()){
			tarefa.setTitulo(tarefa.getTitulo().toUpperCase());
			return tarefaRepository.save(tarefa);
		}
		throw new IllegalArgumentException("Tarefa já cadastrada no projeto.");
	}
}
