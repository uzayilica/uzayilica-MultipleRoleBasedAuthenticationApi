package com.uzay.multiplerolebasedauthenticationapia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @GetMapping("/giris-yapmis-kisi")
    public ResponseEntity<?> girisYapmisBilgi(@AuthenticationPrincipal UserDetails userDetails) {

        Map<String,Object> bilgimap = new HashMap<>();
        bilgimap.put("username",userDetails.getUsername());
        bilgimap.put("password",userDetails.getPassword());
        List<String> authoritiesList = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        bilgimap.put("authorities", authoritiesList);




        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities.stream().forEach(System.out::println);
        System.out.println(userDetails.isAccountNonExpired());



        return ResponseEntity.ok().body(bilgimap);
    }


    @GetMapping("/giris-yapmis-kisi2")
    public ResponseEntity<?>  girisYapmisBilgi2(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities.stream().forEach(System.out::println);

        Map<String,Object> bilgimap = new HashMap<>();
        bilgimap.put("username",userDetails.getUsername());
        bilgimap.put("password",userDetails.getPassword());
        List<String> authoritiesList = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        bilgimap.put("authorities", authoritiesList);

        return ResponseEntity.ok().body(bilgimap);

    }



}
