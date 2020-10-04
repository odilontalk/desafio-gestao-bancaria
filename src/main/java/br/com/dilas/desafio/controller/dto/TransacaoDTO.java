package br.com.dilas.desafio.controller.dto;

import br.com.dilas.desafio.model.TipoOperacao;

import java.math.BigDecimal;
import java.util.Date;

public class TransacaoDTO {
    private Long idTransacao;
    private Date data;
    private BigDecimal saldo;
    private BigDecimal valor;
    private TipoOperacao tipoOperacao;

    public TransacaoDTO() {

    }

    public TransacaoDTO(Date data, Long idTransacao, BigDecimal saldo) {
        this.data = data;
        this.idTransacao = idTransacao;
        this.saldo = saldo;
    }

    public TransacaoDTO(Long idTransacao, TipoOperacao tipoOperacao, Date data, BigDecimal valor) {
        this.idTransacao = idTransacao;
        this.tipoOperacao = tipoOperacao;
        this.data = data;
        this.valor = valor;
    }

    public TransacaoDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }
}
