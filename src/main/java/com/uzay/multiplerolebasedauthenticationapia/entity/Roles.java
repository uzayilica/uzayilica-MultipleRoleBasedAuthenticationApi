package com.uzay.multiplerolebasedauthenticationapia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;


    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    private String roleDescription;

    @JsonBackReference // Rolün kullanıcı referansı
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;



}
