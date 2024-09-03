package com.uzay.multiplerolebasedauthenticationapia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String username;
    private String message;
    private String jwtToken;
}
