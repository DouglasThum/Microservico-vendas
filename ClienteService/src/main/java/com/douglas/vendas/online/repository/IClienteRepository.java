package com.douglas.vendas.online.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.douglas.vendas.online.domain.Cliente;

public interface IClienteRepository extends MongoRepository<Cliente, String>{
	
	Optional<Cliente> findByCpf(Long cpf);

}
