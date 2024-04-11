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
        String recomendacao = "Recomendação para o cliente " + cliente.getNome() + " (" + cliente.getEmail() + " - CPF: " + cliente.getCpf() + "):\n";
        recomendacao += "Endereço: " + endereco.getCep() + ", " + endereco.getRua() + ", " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getComplemento() + "\n";
        recomendacao += "Tipo de solo: " + solo.getTipo() + "\n";
        recomendacao += "Cultura: " + solo.getCultura() + "\n";
        recomendacao += "Temperatura média: " + clima.getTemperaturaMedia() + "°C\n";
        recomendacao += "Condição climática: " + clima.getDescricao() + "\n";
        recomendacao += "Fertilizante recomendado: " + fertilizante.getNome() + "\n";

        return recomendacao;
    }
}
