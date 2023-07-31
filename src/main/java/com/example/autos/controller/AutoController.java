package com.example.autos.controller;

import com.example.autos.domain.enums.Marca;
import com.example.autos.service.AutoService;
import com.example.autos.service.jpa.sqlserver.entities.AutoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
@RequiredArgsConstructor
@CrossOrigin
public class AutoController {

    private final AutoService autoService;

    @GetMapping({"/list", "/admin/list", "/user/list"})
    public ResponseEntity<List<AutoEntity>> list(){
        List<AutoEntity> autoEntities = autoService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(autoEntities);
    }

    @GetMapping({"/list/{marca}", "/admin/list/{marca}"})
    public ResponseEntity<List<AutoEntity>> listByMarca(@PathVariable(name = "marca") Marca marca){
        return ResponseEntity.status(HttpStatus.OK).body(autoService.listByMarca(marca));
    }

    @PostMapping("/create")
    public ResponseEntity<AutoEntity> save(@RequestBody AutoEntity autoEntity){
        AutoEntity auto = autoService.save(autoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(auto);
    }

    @PutMapping("/edit")
    public ResponseEntity<AutoEntity> edit(@RequestBody EditRequest editRequest){
        AutoEntity autoEntity = new AutoEntity(editRequest.id);

        autoEntity.setMarca(editRequest.marca);
        autoEntity.setModelo(editRequest.modelo);
        autoEntity.setColor(editRequest.color);
        autoEntity.setTipo(editRequest.tipo);
        autoEntity.setPrecioCompra(editRequest.precioCompra);
        autoEntity.setPrecioVenta(editRequest.precioVenta);

        autoEntity = autoService.edit(autoEntity);

        return ResponseEntity.status(HttpStatus.OK).body(autoEntity);
    }

    @PutMapping("/editStock")
    public ResponseEntity<AutoEntity> editStock(@RequestBody EditStockRequest editStockRequest){
        AutoEntity auto = autoService.editStock(editStockRequest.id, editStockRequest.stock);
        return ResponseEntity.status(HttpStatus.OK).body(auto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterRequest filterRequest){
        AutoEntity autoEntity = new AutoEntity();
        autoEntity.setMarca(filterRequest.marca);
        autoEntity.setModelo(filterRequest.modelo);
        autoEntity.setColor(filterRequest.color);
        autoEntity.setTipo(filterRequest.tipo);

        autoService.deleteByFilter(autoEntity);

        return ResponseEntity.status(HttpStatus.OK).body("Elementos eliminados por filtro");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
        autoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino el elemento con id: " + id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<AutoEntity>> searchByFilter(@RequestBody FilterRequest filterRequest){
        AutoEntity autoEntity = new AutoEntity();
        autoEntity.setMarca(filterRequest.marca);
        autoEntity.setModelo(filterRequest.modelo);
        autoEntity.setColor(filterRequest.color);
        autoEntity.setTipo(filterRequest.tipo);

        return ResponseEntity.status(HttpStatus.OK).body(autoService.searchByFilter(autoEntity));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<AutoEntity> searchById(@PathVariable(name = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(autoService.searchById(id));
    }

    @PostMapping("/order")
    public ResponseEntity<AutoEntity> makeAnOrder(@RequestBody OrderRequest orderRequest){
        AutoEntity autoEntity = autoService.makeAnOrder(orderRequest.id, orderRequest.quantity);
        return  ResponseEntity.status(HttpStatus.CREATED).body(autoEntity);
    }

    record EditRequest(Long id, Marca marca, String modelo, String color, String tipo, Double precioCompra, Double precioVenta){}
    record EditStockRequest(Long id, Long stock){}
    record FilterRequest(Marca marca, String modelo, String color, String tipo){}
    record OrderRequest(Long id, int quantity){}
}
