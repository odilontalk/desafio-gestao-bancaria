package br.com.dilas.desafio.controller;

import br.com.dilas.desafio.controller.dto.ContaCriadaDTO;
import br.com.dilas.desafio.controller.dto.CriarContaDTO;
import br.com.dilas.desafio.controller.dto.TransacaoDTO;
import br.com.dilas.desafio.model.Conta;
import br.com.dilas.desafio.model.Pessoa;
import br.com.dilas.desafio.model.TipoConta;
import br.com.dilas.desafio.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @PostMapping
    public ContaCriadaDTO criar(@RequestBody CriarContaDTO dadosCadastro) {
        TipoConta tipoConta = dadosCadastro.getTipoConta();

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dadosCadastro.getCpf());
        pessoa.setNome(dadosCadastro.getNome());
        pessoa.setDataNascimento(dadosCadastro.getDataNascimento());

        Conta novaConta = contaService.criar(pessoa, tipoConta);

        ContaCriadaDTO contaCriadaDTO = new ContaCriadaDTO();
        contaCriadaDTO.setId(novaConta.getId());
        contaCriadaDTO.setDataCriacao(novaConta.getDataCriacao());

        return contaCriadaDTO;
    }

    @PostMapping("/{idConta}/deposito")
    public TransacaoDTO deposito(@PathVariable("idConta") Long idConta, @RequestBody TransacaoDTO transacaoDTO) {
        return contaService.deposito(idConta, transacaoDTO.getValor());
    }

    @GetMapping("/{idConta}/saldo")
    public TransacaoDTO consultarSaldo(@PathVariable("idConta") Long idConta) {
        return contaService.consultarSaldo(idConta);
    }

    @PostMapping("/{idConta}/saque")
    public TransacaoDTO saque(@PathVariable("idConta") Long idConta, @RequestBody TransacaoDTO transacaoDTO) {
        return contaService.saque(idConta, transacaoDTO.getValor());
    }

    @PostMapping("/{idConta}/bloquear")
    public void bloquear(@PathVariable("idConta") Long idConta) {
        contaService.bloquear(idConta);
    }

    @GetMapping("/{idConta}/extrato")
    public List<TransacaoDTO> extrato(@PathVariable("idConta") Long idConta) {
        return contaService.extrato(idConta);
    }
}
