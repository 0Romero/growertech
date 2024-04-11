package com.example.growertech.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Entity
public class Solo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O Tipo do solo  é obrigatório")
    private String tipo;
    
    @NotBlank(message = "a Cultura plantada é obrigatória por exemplo : Batata")
    private String cultura; 

    @OneToOne(mappedBy = "solo", cascade = CascadeType.ALL)
    private Recomendacao recomendacao;

    public Solo( String tipo, String cultura) { 
        
        this.tipo = tipo;
        this.cultura = cultura;
    }
}