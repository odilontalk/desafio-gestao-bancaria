package br.com.dilas.desafio.repository;

import br.com.dilas.desafio.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

}
