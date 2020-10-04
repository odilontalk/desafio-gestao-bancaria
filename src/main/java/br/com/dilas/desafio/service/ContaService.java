package br.com.dilas.desafio.service;

import br.com.dilas.desafio.controller.dto.TransacaoDTO;
import br.com.dilas.desafio.model.*;
import br.com.dilas.desafio.repository.ContaRepository;
import br.com.dilas.desafio.repository.PessoaRepository;
import br.com.dilas.desafio.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Conta criar(Pessoa pessoa, TipoConta tipoConta) {
        // TODO verificar se a pessoa existe pesquisando pelo CPF, antes de tentar salvar
        Pessoa novaPessoa = pessoaRepository.save(pessoa);

        Conta novaConta = new Conta();
        novaConta.setPessoa(novaPessoa);
        novaConta.setDataCriacao(new Date());
        novaConta.setFlagAtivo(true);
        novaConta.setSaldo(BigDecimal.ZERO);
        novaConta.setLimiteSaqueDiario(new BigDecimal(1000));
        novaConta.setTipoConta(tipoConta.ordinal());

        novaConta = contaRepository.save(novaConta);

        return novaConta;
    }

    public TransacaoDTO deposito(Long idConta, BigDecimal valor) {
        Optional<Conta> contaLocalizada = recuperarConta(idConta);
        TransacaoDTO transacaoDTO = null;

        Conta conta = contaLocalizada.get();

        Transacao transacaoDeposito = new Transacao();
        transacaoDeposito.setConta(conta);
        transacaoDeposito.setDataTransacao(new Date());
        transacaoDeposito.setValor(valor); // TODO não devo confiar na entrada do usuário, validar

        transacaoDeposito = transacaoRepository.save(transacaoDeposito);

        conta.setSaldo(conta.getSaldo().add(valor.abs()));
        contaRepository.save(conta);

        transacaoDTO = new TransacaoDTO(new Date(), transacaoDeposito.getIdTransacao(), conta.getSaldo());

        return transacaoDTO;
    }

    public TransacaoDTO consultarSaldo(Long idConta) {
        Optional<Conta> contaLocalizada = recuperarConta(idConta);

        Conta conta = contaLocalizada.get();

        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setSaldo(conta.getSaldo());

        return transacaoDTO;
    }

    public TransacaoDTO saque(Long idConta, BigDecimal valor) {
        Optional<Conta> contaLocalizada = recuperarConta(idConta);
        TransacaoDTO transacaoDTO = null;

        Conta conta = contaLocalizada.get();

        Transacao transacaoSaque = new Transacao();
        transacaoSaque.setConta(conta);
        transacaoSaque.setDataTransacao(new Date());
        transacaoSaque.setValor(valor); // TODO não devo confiar na entrada do usuário, validar

        transacaoSaque = transacaoRepository.save(transacaoSaque);

        conta.setSaldo(conta.getSaldo().subtract(valor.abs()));
        contaRepository.save(conta);

        transacaoDTO = new TransacaoDTO(new Date(), transacaoSaque.getIdTransacao(), conta.getSaldo());

        return transacaoDTO;
    }

    public void bloquear(Long idConta) {
        Optional<Conta> contaLocalizada = recuperarConta(idConta);

        Conta conta = contaLocalizada.get();
        conta.setFlagAtivo(false);

        contaRepository.save(conta);
    }

    public List<TransacaoDTO> extrato(Long idConta) {
        Optional<Conta> contaLocalizada = recuperarConta(idConta);
        Conta conta = contaLocalizada.get();

        List<Transacao> listaTransacao = transacaoRepository.findAllByContaIdOrderByDataTransacaoDesc(conta.getId());
        List<TransacaoDTO> retorno = new ArrayList<>();

        for (Transacao transacao: listaTransacao) {
            TransacaoDTO transacaoDTO = new TransacaoDTO();
            TipoOperacao tipoOperacao = null;

            // verifica se o valor da transacao é positivo (deposito)
            if (transacao.getValor().compareTo(BigDecimal.ZERO) > 0) {
                tipoOperacao = TipoOperacao.DEPOSITO;
            } else {
                tipoOperacao = TipoOperacao.SAQUE;
            }

            transacaoDTO.setIdTransacao(transacao.getIdTransacao());
            transacaoDTO.setData(transacao.getDataTransacao());
            transacaoDTO.setValor(transacao.getValor());
            transacaoDTO.setTipoOperacao(tipoOperacao);

            retorno.add(transacaoDTO);
        }

        return retorno;
    }

    private Optional<Conta> recuperarConta(Long idConta) {
        Optional<Conta> contaLocalizada = contaRepository.findById(idConta);

        if (contaLocalizada.isEmpty()) {
            // TODO lançar exceção ContaNaoEncontradaException para dizer que a conta não foi localizada
        } else {
            Conta conta = contaLocalizada.get();

            if (!conta.isFlagAtivo()) {
                // TODO lançar exceção ContaBloqueadaException
            }
        }

        return contaLocalizada;
    }

}
