package com.uzay.multiplerolebasedauthenticationapia.dto;

import com.uzay.multiplerolebasedauthenticationapia.entity.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class RegisterRequestDto {


    @NotBlank(message = "boş bırakılamaz")
    private String username;

    @NotBlank(message = "boş bırakılamaz")
    private String password;







}
