package com.scp.projetos.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "scp01_projeto")
public class Projeto implements Serializable {

	private static final long serialVersionUID = -9088289087004201553L;

	@Id
	@Column(name = "scp01_cod_projeto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;

	@Column(name = "scp01_titulo")
	@NotEmpty
	@NotNull
	private String titulo;

	@Column(name = "scp01_previsao_entrega")
	@NotNull
	private LocalDate previsaoEntrega;
}
