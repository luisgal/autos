package com.example.autos.service.jpa.sqlserver.entities;

import com.example.autos.domain.enums.Marca;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "AUTO", schema = "dbo", catalog = "LOGINEXC")
@Getter
@Setter
@NoArgsConstructor
public class AutoEntity {
    @Id
    @Column(name = "ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "MARCA")
    private Marca marca;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "STOCK")
    private Long stock;
    @Column(name = "NUM_VENDIDOS")
    private Integer numVendidos;
    @Column(name = "PRECIO_COMPRA")
    private Double precioCompra;
    @Column(name = "PRECIO_VENTA")
    private Double precioVenta;
    @Column(name = "FECHA_VENTA")
    private Timestamp fechaVenta;
    @Column(name = "GANANCIAS")
    private Double ganancias;

    public AutoEntity(Long id) {
        this.id = id;
    }
}
