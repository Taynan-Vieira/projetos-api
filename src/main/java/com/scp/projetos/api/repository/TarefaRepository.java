package com.scp.projetos.api.repository;

import com.scp.projetos.api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query(value = "select t from Tarefa t inner join Projeto p on t.projeto.id = p.id where t.titulo like upper" +
			"(concat('%',:titulo, '%')) and t.projeto.id = :id")
	Optional<Tarefa> buscarTarefaPorTituloEProjeto(String titulo, Long id);

	@Query(value = "select t from Tarefa t where t.titulo like upper(concat('%', :titulo, '%'))")
	List<Tarefa> buscarTarefaPorTitulo(String titulo);

}
