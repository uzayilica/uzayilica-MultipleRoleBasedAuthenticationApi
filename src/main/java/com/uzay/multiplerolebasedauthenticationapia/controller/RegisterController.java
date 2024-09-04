package com.uzay.multiplerolebasedauthenticationapia.controller;

import com.uzay.multiplerolebasedauthenticationapia.dto.RegisterRequestDto;
import com.uzay.multiplerolebasedauthenticationapia.entity.RoleEnum;
import com.uzay.multiplerolebasedauthenticationapia.entity.Roles;
import com.uzay.multiplerolebasedauthenticationapia.entity.Users;
import com.uzay.multiplerolebasedauthenticationapia.repository.RoleRepository;
import com.uzay.multiplerolebasedauthenticationapia.repository.UserRepository;
import com.uzay.multiplerolebasedauthenticationapia.security.MyUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterController(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("public/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
try {
    UserDetails userDetails = myUserDetailsService.loadUserByUsername(registerRequestDto.getUsername());
    return ResponseEntity.badRequest().body("Username already exists");

}
catch (UsernameNotFoundException e) {
    Users user = new Users();
    user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
    user.setUsername(registerRequestDto.getUsername());
    Users savedUser = userRepository.save(user);

    Roles roles =new Roles();
    roles.setRoleDescription("user hesabı");
    roles.setRoleName(RoleEnum.ROLE_USER);
    roles.setUser(savedUser);
    roleRepository.save(roles);



    RegisterRequestDto registerRequestDto1 =
            new RegisterRequestDto(savedUser.getUsername(),"kaydedildi" );
return ResponseEntity.ok(registerRequestDto1);

}
catch (Exception e){
    return  ResponseEntity.badRequest().body(e.getMessage());
}



    }




}
