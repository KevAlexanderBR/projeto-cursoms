package io.hithub.cursodsousa.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hithub.cursodsousa.mscartoes.domain.Cartao;
import io.hithub.cursodsousa.mscartoes.domain.ClienteCartao;
import io.hithub.cursodsousa.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import io.hithub.cursodsousa.mscartoes.infra.reposity.CartaoRepository;
import io.hithub.cursodsousa.mscartoes.infra.reposity.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(String payload) {
        try {
            var mapper = new ObjectMapper();

            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);
        } catch (JsonProcessingException e) {
            log.info("Erro ao receber solicitacao de emissao de cartao: {}", e.getMessage());
        }

    }
}
