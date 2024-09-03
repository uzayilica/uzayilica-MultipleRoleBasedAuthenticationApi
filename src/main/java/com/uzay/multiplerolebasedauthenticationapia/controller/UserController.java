package com.uzay.multiplerolebasedauthenticationapia.controller;

import com.uzay.multiplerolebasedauthenticationapia.dto.LoginRequestDto;
import com.uzay.multiplerolebasedauthenticationapia.dto.LoginResponseDto;
import com.uzay.multiplerolebasedauthenticationapia.dto.RegisterRequestDto;
import com.uzay.multiplerolebasedauthenticationapia.dto.RegisterResponseDto;
import com.uzay.multiplerolebasedauthenticationapia.entity.Users;
import com.uzay.multiplerolebasedauthenticationapia.exception.UserNameNotFoundException;
import com.uzay.multiplerolebasedauthenticationapia.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//****************************************
//******** Bu Sınıf Tamamen Test İçindir
//**********************
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("add-user")
    public ResponseEntity<RegisterResponseDto> addUser(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        Users user = new Users();
        user.setPassword(registerRequestDto.getPassword());
        user.setUsername(registerRequestDto.getUsername());
        Users savedUser = userRepository.save(user);
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setUsername(savedUser.getUsername());
        registerResponseDto.setMesaj("kullanıcı başarıyla eklendi");
        return ResponseEntity.ok(registerResponseDto);
    }

    @GetMapping("get-user/{id}")
    public ResponseEntity<RegisterResponseDto>getUser(@PathVariable long id) throws Exception {
        Users findedUser = userRepository.findById(id).orElseThrow(() -> new UserNameNotFoundException("Kullanıcı bulunamadı"));
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setUsername(findedUser.getUsername());
        registerResponseDto.setMesaj("kullanıcı adı ekrana getirildi");
        return ResponseEntity.ok(registerResponseDto);
    }

    @GetMapping("get-user")
    public ResponseEntity<List<RegisterResponseDto>> getAllUser(){
        List<Users> allUser = userRepository.findAll();
        List<RegisterResponseDto> responseDtos = allUser.stream()
                .map(val -> new RegisterResponseDto(val.getUsername(), "kayıtlı"))
                .toList();
        return ResponseEntity.ok(responseDtos);

    }


    @DeleteMapping("get-user/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable long id) throws Exception {
        Users findedUser = userRepository.findById(id).orElseThrow(() -> new UserNameNotFoundException("Kullanıcı bulunamadı"));
       String silinenKullaniciAdi = findedUser.getUsername();
        userRepository.delete(findedUser);
        return ResponseEntity.ok(silinenKullaniciAdi+"başarıyla silindi");
    }


    @Transactional
    @DeleteMapping("get-user-by-name/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        Users bulunan = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNameNotFoundException("Kullanıcı bulunamadı"));

        userRepository.deleteByUsername(bulunan.getUsername());
        return ResponseEntity.ok("Başarıyla silindi");
    }

    @PutMapping("get-user/{id}")
    public ResponseEntity<RegisterResponseDto> UpdateUser(@PathVariable long id,@RequestBody Users user) throws Exception {
        Users findedUser = userRepository.findById(id).orElseThrow(() -> new UserNameNotFoundException("kullanıcı bulunamadı"));
        findedUser.setUsername(user.getUsername());
        findedUser.setPassword(user.getPassword());
        Users save = userRepository.save(findedUser);
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setUsername(save.getUsername());
        registerResponseDto.setMesaj("kullanıcı bilgileri değişti");
        return ResponseEntity.ok(registerResponseDto);

    }





}
