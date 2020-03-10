package com.scp.projetos.api.repository;

import com.scp.projetos.api.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
