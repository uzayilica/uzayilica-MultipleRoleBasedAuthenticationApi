package com.uzay.multiplerolebasedauthenticationapia.controller;

import com.uzay.multiplerolebasedauthenticationapia.dto.LoginRequestDto;
import com.uzay.multiplerolebasedauthenticationapia.dto.LoginResponseDto;
import com.uzay.multiplerolebasedauthenticationapia.jwt.JwtService;
import com.uzay.multiplerolebasedauthenticationapia.security.MyUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginController(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/public/login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginRequestDto loginRequestDto){

        try {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequestDto.getUsername());

            if(passwordEncoder.matches(loginRequestDto.getPassword(), userDetails.getPassword())){
                // artık girişi başarılı demektir

                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
                SecurityContextHolder.getContext().setAuthentication( authenticate);
                String token = jwtService.generateToken(userDetails);
                return ResponseEntity.ok(token);
            }
            else {
                return ResponseEntity.badRequest().body("şifre hatalı");
            }

        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("kullanıcı bulunamadı register olun ");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("bilinmeyen hata oluştu");
        }
    }



}
