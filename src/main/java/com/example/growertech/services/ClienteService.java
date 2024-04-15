package com.example.growertech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.growertech.model.Cliente;
import com.example.growertech.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente novoCliente) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            novoCliente.setId(id);
            return clienteRepository.save(novoCliente);
        }
        return null;
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    public Cliente buscarClientePorCpf(String cpf) {
       
        return clienteRepository.findByCpf(cpf);
    }
}