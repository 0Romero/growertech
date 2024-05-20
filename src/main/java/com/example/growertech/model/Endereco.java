package com.example.growertech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{endereco.rua.notblank}")
    private String rua;

    @NotBlank(message = "{endereco.bairro.notblank}")
    private String bairro;
    
    private String complemento;

    @NotBlank(message = "{endereco.cep.notblank}")
    private String cep;

    @Positive(message = "{endereco.numero.positive}")
    private Integer numero;

    @NotBlank(message = "{endereco.cidade.notblank}")
    private String cidade;
    
    @NotBlank(message = "{endereco.uf.notblank}")
    private String uf;

    
}
