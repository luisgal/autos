package com.example.autos.service.jpa.sqlserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SEC_USER", schema = "dbo", catalog = "LOGINEXC")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "ID_USER")
    private Long idUser;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @ManyToOne
    @JoinColumn(name = "ID_ROLE")
    private RoleEntity role;

    public UserEntity(Long idUser){
        this.idUser = idUser;
    }
}
