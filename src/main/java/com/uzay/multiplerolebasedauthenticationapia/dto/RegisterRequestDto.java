package com.uzay.multiplerolebasedauthenticationapia.dto;

import com.uzay.multiplerolebasedauthenticationapia.entity.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class RegisterRequestDto {


    @NotBlank(message = "boş bırakılamaz")
    private String username;

    @NotBlank(message = "boş bırakılamaz")
    private String password;







}
