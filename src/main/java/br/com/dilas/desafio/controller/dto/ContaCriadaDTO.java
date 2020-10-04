package br.com.dilas.desafio.controller.dto;

import java.util.Date;
import java.util.StringJoiner;

public class ContaCriadaDTO {
    private Long id;
    private Date dataCriacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContaCriadaDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dataCriacao=" + dataCriacao)
                .toString();
    }
}
