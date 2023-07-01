package io.hithub.cursodsousa.msavaliadorcredito.application.ex;

import io.hithub.cursodsousa.msavaliadorcredito.domain.model.DadosCliente;

public class DadosClienteNotFoundException extends Exception {
    public DadosClienteNotFoundException(String cpf){
        super("Dados do cliente não encontrados para o cpf informado: "+cpf);
    }

}
