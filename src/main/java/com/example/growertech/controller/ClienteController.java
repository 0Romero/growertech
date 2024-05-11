package com.example.growertech.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.growertech.dto.RecomendacaoDTO;
import com.example.growertech.model.Cliente;
import com.example.growertech.model.Endereco;
import com.example.growertech.model.Recomendacao;
import com.example.growertech.services.ClienteService;
import com.example.growertech.services.RecomendacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestControllerAdvice
@RequestMapping("/clientes")
@Api(tags = "Clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final RecomendacaoService recomendacaoService;


    public ClienteController(ClienteService clienteService, RecomendacaoService recomendacaoService) {
        this.clienteService = clienteService;
        this.recomendacaoService = recomendacaoService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return result.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @ApiOperation("Criar um novo cliente")
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @ApiOperation("Listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @ApiOperation("Buscar cliente por ID")
    @GetMapping("/searchId/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @ApiOperation("Atualizar cliente")
    @PutMapping("/updateId/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return clienteAtualizado != null ? ResponseEntity.ok(clienteAtualizado) : ResponseEntity.notFound().build();
    }

    @ApiOperation("Deletar cliente")
    @DeleteMapping("/deleteId/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Buscar cliente por CPF")
    @GetMapping("/searchCpf/{cpf}")
    public ResponseEntity<Cliente> findClienteByCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @ApiOperation("Cadastrar endereço para cliente")
    @PostMapping("/enderecoCpf/{cpf}/cadastroEndereco")
    public ResponseEntity<?> cadastrarEndereco(@PathVariable String cpf, @Valid @RequestBody Endereco endereco) {
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente != null) {
            if (cliente.getEndereco() != null) {
                return ResponseEntity.badRequest().body("O cliente já possui um endereço cadastrado.");
            }
            cliente.setEndereco(endereco);
            Cliente clienteComEndereco = clienteService.atualizarCliente(cliente.getId(), cliente);
            return ResponseEntity.ok(clienteComEndereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation("Obter endereço do cliente")
    @GetMapping("/enderecocpf/{cpf}/buscarEndereco")
    public ResponseEntity<Endereco> getEnderecoDoCliente(@PathVariable("cpf") String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        if (cliente != null && cliente.getEndereco() != null) {
            Endereco endereco = cliente.getEndereco();
            return ResponseEntity.ok(endereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Gerar recomendação para o cliente")
    @GetMapping("/recomendacaoCpf/{cpf}/gerarRecomendacao")
    public ResponseEntity<?> gerarRecomendacao(@PathVariable String cpf) {
        try {
            Cliente cliente = clienteService.findByCpf(cpf);
            if (cliente != null && cliente.getEndereco() != null) {
                Recomendacao recomendacao = recomendacaoService.gerarRecomendacao(cliente.getId());
                if (recomendacao != null) {
                    RecomendacaoDTO recomendacaoDTO = new RecomendacaoDTO(recomendacao.getTipoSolo(), recomendacao.getClima(),
                            recomendacao.getCultura(), recomendacao.getFertilizante(), recomendacao.getRecomendacaoSolo(),
                            recomendacao.getTemperaturaMedia(), recomendacao.getRecomendacaoFertilizante());
                    return ResponseEntity.ok(recomendacaoDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar recomendação.");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            // Lidar com exceções de forma mais específica aqui, se necessário
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a solicitação.");
        }
    }
}
