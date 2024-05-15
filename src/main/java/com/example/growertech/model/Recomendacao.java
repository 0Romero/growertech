package com.example.growertech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Recomendacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{cliente.tiposolo.notblank}")
    private String tipoSolo;

    @NotBlank(message = "{cliente.clima.notblank}")
    private String clima;

    @NotBlank(message = "{cliente.cultura.notblank}")
    private String cultura;

    @NotBlank(message = "{cliente.fertilizante.notblank}")
    private String fertilizante;

    @NotBlank(message = "A recomendação para o solo é obrigatória")
    @Size(max = 255, message = "A recomendação para o solo deve ter no máximo 255 caracteres")
    private String recomendacaoSolo;

    @ManyToOne
    private Cliente cliente;

    
    private int temperaturaMedia;

    private String recomendacaoFertilizante;

    // Adicione um construtor que corresponda aos parâmetros necessários
    public Recomendacao(Cliente cliente, String tipoSolo, String clima, String cultura, String fertilizante, int temperaturaMedia, String recomendacaoSolo, String recomendacaoFertilizante) {
        this.cliente = cliente;
        this.tipoSolo = tipoSolo;
        this.clima = clima;
        this.cultura = cultura;
        this.fertilizante = fertilizante;
        this.temperaturaMedia = temperaturaMedia;
        this.recomendacaoSolo = recomendacaoSolo;
        this.recomendacaoFertilizante = recomendacaoFertilizante;
    }
    
}