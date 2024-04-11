package com.example.growertech.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.growertech.dto.RecomendacaoDTO;
import com.example.growertech.model.Clima;
import com.example.growertech.model.Insumo;
import com.example.growertech.model.Solo;
import com.example.growertech.repository.ClimaRepository;
import com.example.growertech.repository.InsumoRepository;
import com.example.growertech.repository.SoloRepository;

@Service
public class RecomendacaoService {

    @Autowired
    private SoloRepository soloRepository;

    @Autowired
    private ClimaRepository climaRepository;

    @Autowired
    private InsumoRepository insumoRepository;

    public List<Insumo> recomendarInsumos(RecomendacaoDTO recomendacaoDTO) {
        // Obter informações do solo e do clima
        Solo solo = soloRepository.findByTipoAndCultura(recomendacaoDTO.getTipoSolo(), recomendacaoDTO.getCultura());
        Clima clima = climaRepository.findByDescricao(recomendacaoDTO.getDescricaoClima());

        // Lógica de recomendação específica para diferentes culturas
        List<Insumo> insumosRecomendados = new ArrayList<>();

        // Verificar temperatura média e recomendar insumos com base nisso
        if (clima.getTemperaturaMedia() < 20) {
            insumosRecomendados.add(InsumoRepository.findByNome("Adubo Fosfatado"));
        } else {
            insumosRecomendados.add(InsumoRepository.findByNome("Adubo Nitrogenado"));
        }

        // Verificar tipo de solo e recomendar insumos com base nisso
        if (solo.getTipo().equals("Argiloso")) {
            insumosRecomendados.add(InsumoRepository.findByNome("Calcário"));
        } else {
            insumosRecomendados.add(InsumoRepository.findByNome("Fertilizante Potássico"));
        }

        // Verificar culturas específicas e recomendar insumos adicionais
        if (recomendacaoDTO.getCultura().equalsIgnoreCase("Batata")) {
            insumosRecomendados.add(InsumoRepository.findByNome("Inseticida para Larvas"));
        } else if (recomendacaoDTO.getCultura().equalsIgnoreCase("Trigo")) {
            insumosRecomendados.add(InsumoRepository.findByNome("Herbicida para Gramíneas"));
        } else if (recomendacaoDTO.getCultura().equalsIgnoreCase("Cana de Açúcar")) {
            insumosRecomendados.add(InsumoRepository.findByNome("Fungicida para Míldio"));
        }

        return insumosRecomendados;
    }
}
