package com.example.growertech.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Cliente extends RepresentationModel<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{cliente.nome.notblank}")
    private String nome;

    @NotBlank(message = "{cliente.cpf.notblank}")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "{cliente.cpf.pattern}")
    private String cpf;

    @NotBlank(message = "{cliente.email.notblank}")
    @Email(message = "{cliente.email.email}")
    private String email;

    @NotBlank(message = "{cliente.tiposolo.notblank}")
    private String tipoSolo;

    @NotBlank(message = "{cliente.clima.notblank}")
    private String clima;

    @NotBlank(message = "{cliente.cultura.notblank}")
    private String cultura;

    @NotBlank(message = "{cliente.fertilizante.notblank}")
    private String fertilizante;

    @NotNull(message = "{cliente.temperaturamedia.notnull}")
    private Integer temperaturaMedia;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    // Lista de links do HATEOAS
    @Transient // Para evitar persistência no banco de dados
    private List<Link> customLinks = new ArrayList<>();

    // Método para adicionar um link personalizado
    public void addCustomLink(Link link) {
        customLinks.add(link);
    }

    // Método personalizado para obter os links personalizados
    public List<Link> getCustomLinks() {
        return customLinks;
    }
}
