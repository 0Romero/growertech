package com.example.growertech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.growertech.dto.RecomendacaoDTO;
import com.example.growertech.model.Cliente;
import com.example.growertech.model.Recomendacao;
import com.example.growertech.services.ClienteService;
import com.example.growertech.services.RecomendacaoService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RecomendacaoService recomendacaoService;

    // Endpoint para criar um cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    // Endpoint para buscar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    // Endpoint para buscar um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para atualizar um cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        if (clienteAtualizado != null) {
            return ResponseEntity.ok(clienteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para gerar recomendação para um cliente
    @GetMapping("/{id}/recomendacao")
    public ResponseEntity<RecomendacaoDTO> gerarRecomendacao(@PathVariable Long id) {
        Recomendacao recomendacao = recomendacaoService.gerarRecomendacao(id);
        if (recomendacao != null) {
            RecomendacaoDTO recomendacaoDTO = new RecomendacaoDTO(recomendacao.getTipoSolo(), recomendacao.getClima(),
                                                                 recomendacao.getCultura(), recomendacao.getFertilizante(),
                                                                 recomendacao.getRecomendacaoSolo(),
                                                                 recomendacao.getTemperaturaMedia(),
                                                                 recomendacao.getRecomendacaoFertilizante());
            return ResponseEntity.ok(recomendacaoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}