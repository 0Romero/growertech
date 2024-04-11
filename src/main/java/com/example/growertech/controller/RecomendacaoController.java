package com.example.growertech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.growertech.model.Cliente;
import com.example.growertech.model.Clima;
import com.example.growertech.model.Endereco;
import com.example.growertech.model.Fertilizante;
import com.example.growertech.model.Recomendacao;
import com.example.growertech.model.Solo;

@RestController
public class RecomendacaoController {

    @GetMapping("/recomendacao")
    public String obterRecomendacao(
            @RequestParam String nomeCliente,
            @RequestParam String cpfCliente,
            @RequestParam String emailCliente,
            @RequestParam String cepEndereco,
            @RequestParam String ruaEndereco,
            @RequestParam String cidadeEndereco,
            @RequestParam String bairroEndereco,
            @RequestParam String complementoEndereco,
            @RequestParam String tipoSolo,
            @RequestParam String culturaSolo,
            @RequestParam String nomeFertilizante,
            @RequestParam int temperaturaMediaClima,
            @RequestParam String descricaoClima) {

      
        Cliente cliente = new Cliente(nomeCliente, cpfCliente, emailCliente);
        Endereco endereco = new Endereco(cepEndereco, ruaEndereco, cidadeEndereco, bairroEndereco, complementoEndereco);
        Solo solo = new Solo(tipoSolo, culturaSolo);
        Fertilizante fertilizante = new Fertilizante(nomeFertilizante);
        Clima clima = new Clima(temperaturaMediaClima, descricaoClima);

        Recomendacao recomendacao = new Recomendacao(cliente, solo, fertilizante, endereco, clima);
        return recomendacao.gerarRecomendacao();
    }
}
