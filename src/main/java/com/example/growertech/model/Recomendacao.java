package com.example.growertech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Recomendacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Cliente cliente; 
    
    @OneToOne
    private Solo solo; 
    
    @OneToOne
    private Fertilizante fertilizante;
    
    @OneToOne
    private Endereco endereco;
    
    @OneToOne
    private Clima clima; 

    public Recomendacao(Cliente cliente, Solo solo, Fertilizante fertilizante, Endereco endereco, Clima clima) {
        this.cliente = cliente;
        this.solo = solo; 
        this.fertilizante = fertilizante;
        this.endereco = endereco;
        this.clima = clima;
    }

    public String gerarRecomendacao() {
        // Inicializa a string de recomendação
        StringBuilder recomendacao = new StringBuilder();
        
        // Verifica o tipo de solo e faz uma recomendação com base nisso
        if (solo.getTipo().equalsIgnoreCase("Argiloso")) {
            recomendacao.append("Recomenda-se realizar a adição de material orgânico para melhorar a estrutura do solo.\n");
        } else if (solo.getTipo().equalsIgnoreCase("Arenoso")) {
            recomendacao.append("Recomenda-se realizar a adição de matéria orgânica para aumentar a capacidade de retenção de água do solo.\n");
        } else {
            recomendacao.append("Recomenda-se realizar um Solo do Tipo Humifero para o seu plantio sendo considerado o mais indicado para o plantio.\n");
        }
        
        // Verifica a temperatura média e a condição climática e faz uma recomendação com base nisso
        if (clima.getTemperaturaMedia() < 20) {
            recomendacao.append("Devido à temperatura mais baixa, é recomendado escolher culturas mais resistentes ao frio.\n");
        } else if (clima.getTemperaturaMedia() > 30) {
            recomendacao.append("Devido à temperatura mais alta, é recomendado escolher culturas mais resistentes ao calor.\n");
        }
        
        // Adiciona mais recomendações com base nos dados do solo e clima, se necessário
        
        // Retorna a recomendação completa
        return recomendacao.toString();
    }
   
}    