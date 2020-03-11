package com.scp.projetos.api.resources;

import com.scp.projetos.api.model.Tarefa;
import com.scp.projetos.api.repository.TarefaRepository;
import com.scp.projetos.api.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaResource {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	public Page<Tarefa> listarProjetos(Pageable pageable) {
		return tarefaRepository.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorCodigo(@PathVariable Long id) {
		return tarefaRepository.findById(id).map(t -> ResponseEntity.ok(t)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity buscarPorTarefa(@PathVariable String titulo) {
		List<Tarefa> tarefas = tarefaService.buscaTarefaPorTitulo(titulo);
		return tarefas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tarefas);
	}

	@PostMapping
	public ResponseEntity<Tarefa> salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirTarefa(@PathVariable Long id) {
		this.tarefaRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
		Tarefa tarefaSalva = tarefaService.atualizarTarefa(id, tarefa);
		return ResponseEntity.ok(tarefaSalva);
	}
}
