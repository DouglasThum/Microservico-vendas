package com.douglas.vendas.online.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import com.douglas.vendas.online.domain.Produto;
import com.douglas.vendas.online.domain.Produto.Status;
import com.douglas.vendas.online.exceptions.EntityNotFoundException;
import com.douglas.vendas.online.repository.IProdutoRepository;

import jakarta.validation.Valid;

public class CadastroProduto {
	
	private IProdutoRepository produtoRepository;
	
	@Autowired
	public CadastroProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto cadastrar(@Valid Produto produto) {
		produto.setStatus(Status.ATIVO);
		return this.produtoRepository.insert(produto);
	}

	public Produto atualizar(@Valid Produto produto) {
		return this.produtoRepository.save(produto);
	}

	public void remover(String id) {
		Produto prod = produtoRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Produto.class, "id", id));
		prod.setStatus(Status.INATIVO);
		this.produtoRepository.save(prod);
	}

}
