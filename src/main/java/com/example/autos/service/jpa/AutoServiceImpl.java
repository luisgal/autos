package com.example.autos.service.jpa;

import com.example.autos.domain.enums.Marca;
import com.example.autos.service.AutoService;
import com.example.autos.service.jpa.sqlserver.entities.AutoEntity;
import com.example.autos.service.jpa.sqlserver.repositories.AutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoServiceImpl implements AutoService {

    private final AutoRepository autoRepository;

    @Override
    public AutoEntity save(AutoEntity autoEntity){
        if (autoRepository.findById(autoEntity.getId()).isPresent()){
            throw new RuntimeException("No se pudo agregar el producto, id repetido");
        }
        return autoRepository.save(autoEntity);
    }

    @Override
    public AutoEntity edit(AutoEntity autoEntity) {
        AutoEntity auto = autoRepository
                .findById(autoEntity.getId())
                .orElseThrow(() -> new RuntimeException("No se edito el auto indicado ya que el no existe ninguno con el id proporcionado"));

        if(autoEntity.getMarca() != null){
            auto.setMarca(autoEntity.getMarca());
        }
        if(autoEntity.getModelo() != null){
            auto.setModelo(autoEntity.getModelo());
        }
        if(autoEntity.getColor() != null){
            auto.setColor(autoEntity.getColor());
        }
        if(autoEntity.getTipo() != null){
            auto.setTipo(autoEntity.getTipo());
        }
        if(autoEntity.getPrecioCompra() != null){
            auto.setPrecioCompra(autoEntity.getPrecioCompra());
        }
        if(autoEntity.getPrecioVenta() != null){
            auto.setPrecioVenta(autoEntity.getPrecioVenta());
        }

        return autoRepository.save(auto);
    }

    @Override
    @Transactional
    public AutoEntity editStock(Long id, Long stock){
        AutoEntity autoEntity = autoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el auto solicitado"));
        autoEntity.setStock(stock);
        return autoEntity;
    }

    @Override
    @Transactional
    public void deleteByFilter(AutoEntity autoEntity) {

        Marca marca = autoEntity.getMarca() != null ? autoEntity.getMarca() : Marca.NULL;
        String modelo = autoEntity.getModelo() != null ? autoEntity.getModelo() : "null";
        String color = autoEntity.getColor() != null ? autoEntity.getColor() : "null";
        String tipo = autoEntity.getTipo() != null ? autoEntity.getTipo() : "null";

        autoRepository.deleteByMarcaOrModeloOrColorOrTipo(marca,modelo,color,tipo);
    }

    @Override
    public void deleteById(Long id) {
        autoRepository.deleteById(id);
    }

    @Override
    public AutoEntity searchById(Long id) {
        return autoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el auto con el id: " + id));
    }

    @Override
    public List<AutoEntity> searchByFilter(AutoEntity autoEntity) {

        Marca marca = autoEntity.getMarca() != null ? autoEntity.getMarca() : Marca.NULL;
        String modelo = autoEntity.getModelo() != null ? autoEntity.getModelo() : "null";
        String color = autoEntity.getColor() != null ? autoEntity.getColor() : "null";
        String tipo = autoEntity.getTipo() != null ? autoEntity.getTipo() : "null";

        return autoRepository.findByMarcaOrModeloOrColorOrTipo(marca, modelo, color, tipo);
    }

    @Override
    public List<AutoEntity> listAll() {
        return autoRepository.findAll();
    }

    @Override
    public List<AutoEntity> listByMarca(Marca marca) {
        return autoRepository.findByMarca(marca);
    }

    @Override
    @Transactional
    public AutoEntity makeAnOrder(Long id, int quantity) {
        AutoEntity autoEntity = autoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el auto solicitado"));

        double ganancia = autoEntity.getPrecioVenta() - autoEntity.getPrecioCompra();

        autoEntity.setStock(autoEntity.getStock() - quantity);
        autoEntity.setNumVendidos(autoEntity.getNumVendidos() + quantity);
        autoEntity.setFechaVenta(Timestamp.valueOf(LocalDateTime.now()));
        autoEntity.setGanancias(autoEntity.getGanancias() + (ganancia * quantity));
        autoEntity.setFechaVenta(Timestamp.valueOf(LocalDateTime.now()));

        return autoEntity;
    }
}
