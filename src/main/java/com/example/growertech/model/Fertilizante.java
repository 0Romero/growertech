package com.example.growertech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data

@Entity
public class Fertilizante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do fertilizante é obrigatório")
    private String nome;
    
   public Fertilizante(String nome ) {
    
    this.nome = nome;

   }
}