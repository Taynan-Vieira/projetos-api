package com.scp.projetos.api.repository;

import com.scp.projetos.api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query(value = "select t from Tarefa t inner join Projeto p on t.projeto.id = p.id where t.titulo like upper" +
			"(concat('%',:titulo, '%')) and t.projeto.id = :id")
	Optional<Tarefa> buscarTarefaPorTitulo(String titulo, Long id);

	/*select t.scp02_titulo, t.fkscp02scp01_cod_projeto from scp02_tarefa
	t inner join scp01_projeto p on t.fkscp02scp01_cod_projeto = p.scp01_cod_projeto
	where t.scp02_titulo like '%DEFI%' and t.fkscp02scp01_cod_projeto = 1;*/

}
