package com.example.growertech.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Entity
public class Clima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int temperaturaMedia;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    public Clima( int temperaturaMedia, String descricao) {
        this.temperaturaMedia = temperaturaMedia;
        this.descricao = descricao;
    }
}