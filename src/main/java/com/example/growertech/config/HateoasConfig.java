package com.example.growertech.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.growertech.controller.ClienteController;

import java.lang.reflect.Method;

@Configuration
public class HateoasConfig {

    @Bean
    public CustomRequestMappingHandlerMapping customRequestMappingHandlerMapping() {
        return new CustomRequestMappingHandlerMapping();
    }

    private static class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

        @Override
        protected void registerHandlerMethod(Object handler, Method method, org.springframework.web.servlet.mvc.method.RequestMappingInfo mapping) {
            super.registerHandlerMethod(handler, method, mapping);
            if (handler instanceof ClienteController) {
                ClienteController clienteController = (ClienteController) handler;
                String methodName = method.getName();
                if (methodName.equals("criarCliente")) {
                    registerLinkForMethod(method, clienteController, "listarClientes", "self");
                    registerLinkForMethod(method, clienteController, "buscarClientePorId", "buscar_cliente_por_id");
                    registerLinkForMethod(method, clienteController, "atualizarCliente", "atualizar_cliente");
                    registerLinkForMethod(method, clienteController, "deletarCliente", "deletar_cliente");
                    registerLinkForMethod(method, clienteController, "findClienteByCpf", "buscar_cliente_por_cpf");
                    registerLinkForMethod(method, clienteController, "cadastrarEndereco", "cadastrar_endereco");
                    registerLinkForMethod(method, clienteController, "getEnderecoDoCliente", "buscar_endereco");
                    registerLinkForMethod(method, clienteController, "gerarRecomendacao", "gerar_recomendacao");
                }
            }
        }

        private void registerLinkForMethod(Method method, ClienteController controller, String targetMethodName, String rel) {
            Method targetMethod;
            try {
                targetMethod = ClienteController.class.getDeclaredMethod(targetMethodName, Long.class);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Método alvo não encontrado: " + targetMethodName);
            }
            String uriString = "/clientes/{cpf}"; // Modifique de acordo com a sua rota
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(uriString);
            Link link = HateoasUtils.createLink(uriBuilder, rel);
            HateoasUtils.registerLinkForMethod(targetMethod, link);
        }
    }
}
