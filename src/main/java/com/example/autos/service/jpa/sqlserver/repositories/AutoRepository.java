package com.example.autos.service.jpa.sqlserver.repositories;

import com.example.autos.domain.enums.Marca;
import com.example.autos.service.jpa.sqlserver.entities.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoRepository extends JpaRepository<AutoEntity,Long> {
    List<AutoEntity> findByMarca(Marca marca);
    List<AutoEntity> findByMarcaOrModeloOrColorOrTipo(Marca marca, String modelo, String color, String tipo);
    void deleteByMarcaOrModeloOrColorOrTipo(Marca marca, String modelo, String color, String tipo);
}
