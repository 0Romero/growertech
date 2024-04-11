package com.example.growertech.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecomendacaoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String nomeCliente;

    @NotBlank(message = "CPF do cliente é obrigatório")
    private String cpfCliente;

    @NotBlank(message = "Email do cliente é obrigatório")
    private String emailCliente;

    @NotBlank(message = "CEP do endereço é obrigatório")
    private String cepEndereco;

    @NotBlank(message = "Rua do endereço é obrigatória")
    private String ruaEndereco;

    @NotBlank(message = "Cidade do endereço é obrigatória")
    private String cidadeEndereco;

    @NotBlank(message = "Bairro do endereço é obrigatório")
    private String bairroEndereco;

    @NotBlank(message = "Complemento do endereço é obrigatório")
    private String complementoEndereco;

    @NotBlank(message = "Tipo de solo é obrigatório")
    private String tipoSolo;

    @NotBlank(message = "Cultura do solo é obrigatória")
    private String cultura;

    @NotBlank(message = "Nome do fertilizante é obrigatório")
    private String nomeFertilizante;

    @NotNull(message = "Temperatura média do clima é obrigatória")
    private Integer temperaturaMediaClima;

    @NotBlank(message = "Descrição do clima é obrigatória")
    private String descricaoClima;
}
