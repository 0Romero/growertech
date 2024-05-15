package com.example.growertech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.growertech.dto.RecomendacaoDTO;
import com.example.growertech.model.Cliente;
@Service
public class RecomendacaoService {

    @Autowired
    private ClienteService clienteService;

    public RecomendacaoDTO gerarRecomendacao(Long clienteId) {
        Cliente cliente = clienteService.buscarClientePorId(clienteId);
        if (cliente != null && cliente.getEndereco() != null) {
            String tipoSolo = cliente.getTipoSolo();
            String clima = cliente.getClima();
            String cultura = cliente.getCultura();
            String fertilizante = cliente.getFertilizante();
            int temperaturaMedia = cliente.getTemperaturaMedia();
            String recomendacaoSolo = "";
            String recomendacaoFertilizante = "";
            
            switch (tipoSolo) {
                case "Argiloso":
                    recomendacaoSolo = "Use fertilizantes ricos em potássio para melhorar a fertilidade do solo.";
                    break;
                case "Arenoso":
                    recomendacaoSolo = "Adicione matéria orgânica ao solo para aumentar sua capacidade de retenção de água.";
                    break;
                case "Aluvial":
                    recomendacaoSolo = "Considere o uso de fertilizantes de liberação controlada para fornecer nutrientes de forma gradual.";
                    break;
                case "Pedregoso":
                    recomendacaoSolo = "Realize uma análise de solo para determinar a viabilidade de culturas adequadas para esse tipo de solo.";
                    break;
                case "Silte":
                    recomendacaoSolo = "Aplique calcário para corrigir a acidez do solo e melhorar a qualidade do solo.";
                    break;
                case "Orgânico":
                    recomendacaoSolo = "Utilize adubos orgânicos para enriquecer o solo com nutrientes naturais.";
                    break;
                default:
                    recomendacaoSolo = "Recomende-se o Solo Humifero para seu platio.";
            }
            if (temperaturaMedia < 10) {
                recomendacaoFertilizante = "Prefira fertilizantes com liberação lenta de nutrientes para garantir uma nutrição adequada em temperaturas mais baixas.";
            } else if (temperaturaMedia >= 10 && temperaturaMedia < 25) {
                recomendacaoFertilizante = "Escolha fertilizantes balanceados que atendam às necessidades específicas das plantas.";
            } else if (temperaturaMedia >= 25 && temperaturaMedia < 30) {
                recomendacaoFertilizante = "Utilize fertilizantes balanceados que atendam às necessidades específicas das plantas.";
            } else {
                recomendacaoFertilizante = "Utilize fertilizantes com maior teor de nitrogênio para estimular o crescimento das plantas.";
            }

            // Criar e retornar o DTO de recomendação
            return new RecomendacaoDTO(tipoSolo, clima, cultura, fertilizante, recomendacaoSolo, temperaturaMedia, recomendacaoFertilizante);
        } else {
            return null; // Ou lançar uma exceção, dependendo dos requisitos do seu aplicativo
        }
    }
}
