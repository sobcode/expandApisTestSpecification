package com.app.expandapistesttask.config.jwt;

import com.app.expandapistesttask.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser createJwtUser(User user){
        return new JwtUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                true,
                mapRoleToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapRoleToGrantedAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Admin"));

        return authorities;
    }
}
