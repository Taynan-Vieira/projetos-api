package com.scp.projetos.api.resources;

import com.scp.projetos.api.model.Projeto;
import com.scp.projetos.api.model.Tarefa;
import com.scp.projetos.api.repository.TarefaRepository;
import com.scp.projetos.api.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefasResource {

	@Autowired
	private transient TarefaRepository tarefaRepository;

	@Autowired
	private transient TarefaService tarefaService;

	@GetMapping
	public Page<Tarefa> listarProjetos(Pageable pageable) {
		return tarefaRepository.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorCodigo(@PathVariable Long id) {
		Optional tarefa = this.tarefaRepository.findById(id);
		return tarefa.isPresent() ? ResponseEntity.ok(tarefa.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Tarefa> salvarTarefa(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
		Tarefa tarefaSalva = tarefaService.salvarTarefa(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirTarefa(@PathVariable Long id) {
		this.tarefaRepository.deleteById(id);
	}
}
