package br.com.dilas.desafio.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "tb_conta")
public class Conta {

    @Id
    @Column(name = "id_conta")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Basic(optional = false)
    private BigDecimal saldo;

    @Basic(optional = false)
    @Column(name = "limite_saque_diario")
    private BigDecimal limiteSaqueDiario;

    @Basic(optional = false)
    @Column(name = "flag_ativo")
    private boolean flagAtivo;

    @Basic(optional = false)
    @Column(name = "tipo_conta")
    private Integer tipoConta;

    @Basic(optional = false)
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @OneToMany(mappedBy = "conta")
    private List<Transacao> transacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getLimiteSaqueDiario() {
        return limiteSaqueDiario;
    }

    public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
        this.limiteSaqueDiario = limiteSaqueDiario;
    }

    public boolean isFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return flagAtivo == conta.flagAtivo &&
                Objects.equals(id, conta.id) &&
                Objects.equals(pessoa, conta.pessoa) &&
                Objects.equals(saldo, conta.saldo) &&
                Objects.equals(limiteSaqueDiario, conta.limiteSaqueDiario) &&
                Objects.equals(tipoConta, conta.tipoConta) &&
                Objects.equals(dataCriacao, conta.dataCriacao) &&
                Objects.equals(transacoes, conta.transacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pessoa, saldo, limiteSaqueDiario, flagAtivo, tipoConta, dataCriacao, transacoes);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Conta.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("pessoa=" + pessoa)
                .add("saldo=" + saldo)
                .add("limiteSaqueDiario=" + limiteSaqueDiario)
                .add("flagAtivo=" + flagAtivo)
                .add("tipoConta=" + tipoConta)
                .add("dataCriacao=" + dataCriacao)
                .add("transacoes=" + transacoes)
                .toString();
    }
}