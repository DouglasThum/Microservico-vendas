package com.douglas.vendas.online.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.vendas.online.domain.Venda;
import com.douglas.vendas.online.dto.VendaDTO;
import com.douglas.vendas.online.usecase.BuscaVenda;
import com.douglas.vendas.online.usecase.CadastroVenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/venda")
public class VendaResources {
	
	private BuscaVenda buscaVenda;
	
	private CadastroVenda cadastroVenda;
	
	@Autowired
	public VendaResources(BuscaVenda buscaVenda, CadastroVenda cadastroVenda) {
		this.buscaVenda = buscaVenda;
		this.cadastroVenda = cadastroVenda;
	}
	
	@GetMapping
	@Operation(summary = "Lista as vendas cadastradas")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Retorna a lista de clientes"),
		    @ApiResponse(responseCode = "400", description = "Requisição malformada ou erro de sintaxe", 
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
		    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção",
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
		})
	public ResponseEntity<Page<Venda>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscaVenda.buscar(pageable));
	}
	
	@PostMapping
	@Operation(summary = "Iniciar uma venda")
	public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaDTO venda) {
		return ResponseEntity.ok(cadastroVenda.cadastrar(venda));
	}
	
	@PutMapping("/{id}/{codigoProduto}/{quantidade}/addProduto")
	public ResponseEntity<Venda> adicionarProduto(
			@PathVariable(name = "id", required = true) String id,
			@PathVariable(name = "codigoProduto", required = true) String codigoProduto,
			@PathVariable(name = "quantidade", required = true) Integer quantidade) {
		return ResponseEntity.ok(cadastroVenda.adicionarProduto(id, codigoProduto, quantidade));
	}

}
