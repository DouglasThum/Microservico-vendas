package com.douglas.vendas.online.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.vendas.online.domain.Cliente;
import com.douglas.vendas.online.repository.IClienteRepository;

import jakarta.validation.Valid;

@Service
public class CadastroCliente {

private IClienteRepository clienteRepository;
	
	@Autowired
	public CadastroCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente cadastrar(@Valid Cliente cliente) {
		return this.clienteRepository.insert(cliente);
	}

	public Cliente atualizar(@Valid Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public void remover(String id) {
		this.clienteRepository.deleteById(id);
	}
}
