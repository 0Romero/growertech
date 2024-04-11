package com.example.growertech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.growertech.model.Solo;


@Repository
public interface SoloRepository extends JpaRepository <Solo,Long > {
    Solo findById(long Id);

    Solo findByTipoAndCultura(String tipoSolo, String cultura);
}
 
    

