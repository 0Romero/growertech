package com.example.growertech.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.growertech.dto.RecomendacaoDTO;
import com.example.growertech.model.Cliente;
import com.example.growertech.model.Endereco;
import com.example.growertech.services.ClienteService;
import com.example.growertech.services.RecomendacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
@Tag(name  = "clientes")
public class ClienteController {


    @Autowired
    private  ClienteService clienteService;
    @Autowired
    private  RecomendacaoService recomendacaoService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return result.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @Operation(summary = "Criar um novo cliente")
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        addLinks(novoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        
        for (Cliente cliente : clientes) {
            addLinks(cliente);
        }
        
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary="Buscar cliente por ID")
    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente != null) {
            addLinks(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary="Atualizar cliente")
    @PutMapping("{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        if (clienteAtualizado != null) {
            addLinks(clienteAtualizado);
            return ResponseEntity.ok(clienteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletar cliente")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary="Buscar cliente por CPF")
    @GetMapping("cpf/{cpf}")
    public ResponseEntity<Cliente> findClienteByCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente != null) {
            addLinks(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary="Cadastrar endereço para cliente")
    @PostMapping("cpf/{cpf}/cadastroEndereco")
    public ResponseEntity<?> cadastrarEndereco(@PathVariable String cpf, @Valid @RequestBody Endereco endereco) {
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente != null) {
            if (cliente.getEndereco() != null) {
                return ResponseEntity.badRequest().body("O cliente já possui um endereço cadastrado.");
            }
            cliente.setEndereco(endereco);
            Cliente clienteComEndereco = clienteService.atualizarCliente(cliente.getId(), cliente);
            addLinks(clienteComEndereco);
            return ResponseEntity.ok(clienteComEndereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary="Obter endereço do cliente")
    @GetMapping("cpf/{cpf}/buscarEndereco")
    public ResponseEntity<Endereco> getEnderecoDoCliente(@PathVariable("cpf") String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente != null && cliente.getEndereco() != null) {
            Endereco endereco = cliente.getEndereco();
            return ResponseEntity.ok(endereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
    @Operation(summary = "Gerar recomendação para o cliente")
    @GetMapping("cpf/{cpf}/gerarRecomendacao")
    public ResponseEntity<?> gerarRecomendacao(@PathVariable String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente == null || cliente.getEndereco() == null) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            RecomendacaoDTO recomendacaoDTO = recomendacaoService.gerarRecomendacao(cliente.getId());
            if (recomendacaoDTO == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar recomendação.");
            }

            // Criar um objeto que contenha tanto o cliente quanto a recomendação
            Map<String, Object> response = new HashMap<>();
            response.put("cliente", cliente);
            response.put("recomendacao", recomendacaoDTO);

            // Retornar a resposta no corpo da resposta
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação.");
        }
    }
    
    // Método para adicionar links aos clientes
    private void addLinks(Cliente cliente) {
        Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class).slash(cliente.getId()).withSelfRel();
        cliente.add(selfLink);
        // Adicione mais links personalizados conforme necessário
    }
}
