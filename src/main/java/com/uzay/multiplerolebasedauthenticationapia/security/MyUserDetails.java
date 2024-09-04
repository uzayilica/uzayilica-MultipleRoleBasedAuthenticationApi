package com.uzay.multiplerolebasedauthenticationapia.security;

import com.uzay.multiplerolebasedauthenticationapia.entity.Roles;
import com.uzay.multiplerolebasedauthenticationapia.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    Users users;

    public MyUserDetails(Users users) {
        this.users = users;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Roles> roles = users.getRoles();



        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())) // roleName alanını kullanarak
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return users.getPassword();    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
