package com.scp.projetos.api.resources;

import com.scp.projetos.api.model.Projeto;
import com.scp.projetos.api.repository.ProjetoRepository;
import com.scp.projetos.api.service.ProjetoService;
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
@RequestMapping("/projetos")
public class ProjetoResource {

	@Autowired
	private transient ProjetoRepository projetoRepository;

	@Autowired
	private transient ProjetoService projetoService;

	@GetMapping
	public Page<Projeto> listarProjetos(Pageable pageable) {
		return projetoRepository.listarPorPrevisaoDeEntrega(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorCodigo(@PathVariable Long id) {
		Optional projeto = this.projetoRepository.findById(id);
		return projeto.isPresent() ? ResponseEntity.ok(projeto.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Projeto> salvarProjeto(@Valid @RequestBody Projeto projeto, HttpServletResponse response) {
		Projeto projetoSalvo = projetoService.salvarProjeto(projeto);
		return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirProjeto(@PathVariable Long id) {
		this.projetoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @Valid @RequestBody Projeto projeto) {
		Projeto projetoSalvo = projetoService.atualizarProjeto(id, projeto);
		return ResponseEntity.ok(projetoSalvo);
	}

}

