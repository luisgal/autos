package com.example.autos.service.jpa.sqlserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "SEC_ROLE", schema = "dbo", catalog = "LOGINEXC")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {
    @Id
    @Column(name = "ID_ROLE")
    private Long idRole;
    @Basic
    @Column(name = "NAME")
    private String name;
}
