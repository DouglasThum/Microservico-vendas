package com.douglas.vendas.online.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collation = "pt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="Produto", description = "Produto")
public class Produto {
	
	@Schema(description = "Status")
	public enum Status {
		ATIVO, INATIVO;
	}

	@Id
	@Schema(description = "Identificador único", nullable = false)
	private String id;
	
	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	@Schema(description = "Código", nullable = false)
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Nome", nullable = false)
	private String nome;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Schema(description = "descrição", minLength = 1, maxLength = 100, nullable = false)
	private String descricao;
	
	@Schema(description = "valor", nullable = false)
	private BigDecimal valor;
	
	@Schema(description = "status", nullable = false)
	private Status status;
}
