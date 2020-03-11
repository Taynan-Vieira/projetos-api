package com.scp.projetos.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "scp02_tarefa")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = -9088289087004201553L;

	@Id
	@Column(name = "scp02_cod_tarefa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;

	@Column(name = "scp02_titulo")
	@NotEmpty
	@NotNull
	private String titulo;

	@JoinColumn(name = "fkscp02scp01_cod_projeto")
	@ManyToOne
	private Projeto projeto;
}
