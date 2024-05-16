package com.example.growertech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class RecomendacaoDTO extends RepresentationModel<RecomendacaoDTO> {

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

    @Positive
    private int temperaturaMedia;

    @NotBlank
    private String recomendacaoFertilizante;

    // Getters and Setters
    public String getTipoSolo() {
        return tipoSolo;
    }

    public void setTipoSolo(String tipoSolo) {
        this.tipoSolo = tipoSolo;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public String getFertilizante() {
        return fertilizante;
    }

    public void setFertilizante(String fertilizante) {
        this.fertilizante = fertilizante;
    }

    public String getRecomendacaoSolo() {
        return recomendacaoSolo;
    }

    public void setRecomendacaoSolo(String recomendacaoSolo) {
        this.recomendacaoSolo = recomendacaoSolo;
    }

    public int getTemperaturaMedia() {
        return temperaturaMedia;
    }

    public void setTemperaturaMedia(int temperaturaMedia) {
        this.temperaturaMedia = temperaturaMedia;
    }

    public String getRecomendacaoFertilizante() {
        return recomendacaoFertilizante;
    }

    public void setRecomendacaoFertilizante(String recomendacaoFertilizante) {
        this.recomendacaoFertilizante = recomendacaoFertilizante;
    }

    public RecomendacaoDTO(String tipoSolo, String clima, String cultura, String fertilizante, String recomendacaoSolo, int temperaturaMedia, String recomendacaoFertilizante) {
        this.tipoSolo = tipoSolo;
        this.clima = clima;
        this.cultura = cultura;
        this.fertilizante = fertilizante;
        this.recomendacaoSolo = recomendacaoSolo;
        this.temperaturaMedia = temperaturaMedia;
        this.recomendacaoFertilizante = recomendacaoFertilizante;
    }

    public void addLink(Link link) {
        super.add(link);
    }
}
