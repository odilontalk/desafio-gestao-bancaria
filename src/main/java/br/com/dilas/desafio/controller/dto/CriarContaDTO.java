package br.com.dilas.desafio.controller.dto;

import br.com.dilas.desafio.model.TipoConta;

import java.util.Date;
import java.util.StringJoiner;

public class CriarContaDTO {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private TipoConta tipoConta;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CriarContaDTO.class.getSimpleName() + "[", "]")
                .add("cpf='" + cpf + "'")
                .add("nome='" + nome + "'")
                .add("dataNascimento=" + dataNascimento)
                .add("tipoConta='" + tipoConta + "'")
                .toString();
    }
}
