package com.example.growertech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.growertech.model.Clima;


@Repository
public interface ClimaRepository extends JpaRepository <Clima,Long > {
    Clima findById(long Id);

    Clima findByDescricao(String descricaoClima);
}
 
    
