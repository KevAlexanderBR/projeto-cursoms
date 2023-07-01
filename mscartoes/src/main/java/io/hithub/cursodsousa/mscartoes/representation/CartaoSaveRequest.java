package io.hithub.cursodsousa.mscartoes.representation;

import io.hithub.cursodsousa.mscartoes.domain.BandeiraCartao;
import io.hithub.cursodsousa.mscartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}


