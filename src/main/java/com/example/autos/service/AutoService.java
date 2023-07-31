package com.example.autos.service;

import com.example.autos.domain.enums.Marca;
import com.example.autos.service.jpa.sqlserver.entities.AutoEntity;

import java.util.List;

public interface AutoService {
    AutoEntity save(AutoEntity autoEntity);
    AutoEntity edit(AutoEntity autoEntity);
    AutoEntity editStock(Long id, Long stock);
    void deleteByFilter(AutoEntity autoEntity);
    void deleteById(Long id);
    AutoEntity searchById(Long id);
    List<AutoEntity> searchByFilter(AutoEntity autoEntity);
    List<AutoEntity> listAll();
    List<AutoEntity> listByMarca(Marca marca);
    AutoEntity makeAnOrder(Long id, int quantity);
}
