package io.hithub.cursodsousa.mscartoes.application;

import io.hithub.cursodsousa.mscartoes.domain.ClienteCartao;
import io.hithub.cursodsousa.mscartoes.infra.reposity.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
