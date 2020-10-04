package br.com.dilas.desafio.repository;

import br.com.dilas.desafio.model.Transacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {
    List<Transacao> findAllByContaIdOrderByDataTransacaoDesc(Long idConta);
}
