package com.scp.projetos.api.repository;

import com.scp.projetos.api.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

	@Query(value = "select p.titulo from Projeto p where p.titulo like (concat('%', :titulo, '%'))")
	Optional<Projeto> buscarProjetoPorDescricao(String titulo);

	@Query(value = "select p from Projeto p order by p.previsaoEntrega asc")
	Page<Projeto> listarPorPrevisaoDeEntrega(Pageable pageable);

}
