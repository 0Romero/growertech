package com.example.growertech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.example.growertech.controller.ClienteController;
@Configuration
public class HateoasConfig {

    // Correção: Mudança no retorno do método para retornar uma instância de ClienteController
    @Bean
    public ClienteController clienteControllerLinkBuilder(RequestMappingHandlerMapping mapping) {
        return WebMvcLinkBuilder.methodOn(ClienteController.class);
    }
}
