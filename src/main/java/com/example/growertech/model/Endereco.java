package com.example.growertech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cidade é Obrigatoria")
    private String cidade; 
    
    @NotBlank(message = "A rua é obrigatória")
    private String rua;
    
    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;
    
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    @NotBlank(message= "Complemento Obrigatorio...")
    private String complemento;
    
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;



    public Endereco( String cidade, String rua, String bairro, String cep,String complemento) {
        
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep; 
        this.bairro = complemento;

    }
}