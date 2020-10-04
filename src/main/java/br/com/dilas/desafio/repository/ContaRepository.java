package br.com.dilas.desafio.repository;

import br.com.dilas.desafio.model.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<Conta, Long> {

}
