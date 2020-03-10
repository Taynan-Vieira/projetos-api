package com.scp.projetos.api.model;

import lombok.Data;

import javax.persistence.*;
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
	private Long id;

	@Column(name = "scp01_titulo")
	@NotNull
	private String titulo;

	@Column(name = "scp01_previsao_entrega")
	@NotNull
	private LocalDate previsaoEntrega;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Projeto projeto = (Projeto) o;
		return id.equals(projeto.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
