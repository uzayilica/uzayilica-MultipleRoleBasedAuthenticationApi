package com.uzay.multiplerolebasedauthenticationapia.controller;

import com.uzay.multiplerolebasedauthenticationapia.entity.Roles;
import com.uzay.multiplerolebasedauthenticationapia.entity.Users;
import com.uzay.multiplerolebasedauthenticationapia.exception.UserNameNotFoundException;
import com.uzay.multiplerolebasedauthenticationapia.repository.RoleRepository;
import com.uzay.multiplerolebasedauthenticationapia.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class RoleController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RoleController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("add-role")
    public ResponseEntity<?> addRole(@RequestBody Roles role) {
        if (role.getUser() == null || role.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("Geçersiz kullanıcı bilgisi.");
        }

        Users user = userRepository.findById(role.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı"));

        Roles roles = new Roles();
        roles.setRoleName(role.getRoleName());
        roles.setRoleDescription(role.getRoleDescription());
        roles.setUser(user);

        Roles savedRole = roleRepository.save(roles);
        return ResponseEntity.ok("Rol eklendi: " + savedRole.getRoleName());
    }
    @GetMapping("/get-roles")
    public ResponseEntity<?> getAllRoles() {
        List<Roles> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }
    @GetMapping("/get-role/{id}")
    public ResponseEntity<?> getRole(@PathVariable Long id) {
        Roles rol = roleRepository.findById(id).orElseThrow(() -> new UserNameNotFoundException("Rol bulunamadı"));
        return ResponseEntity.ok(rol);
    }
    @DeleteMapping("/get-role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        Roles rol = roleRepository.findById(id).orElseThrow(() -> new UserNameNotFoundException("Rol bulunamadı"));
        roleRepository.deleteById(id);
        return ResponseEntity.ok("rol silindi");
    }


    @PostMapping("/uzay")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }



}
