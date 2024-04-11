package com.example.growertech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.growertech.model.Insumo;

@Repository
public interface InsumoRepository extends JpaRepository <Insumo,Long > {
    Insumo findById(long Id);

    static Insumo findByNome(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }
}
