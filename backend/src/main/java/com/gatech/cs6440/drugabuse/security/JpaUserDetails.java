package com.gatech.cs6440.drugabuse.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class JpaUserDetails implements UserDetails {

    private String userName;

    public JpaUserDetails(String userName) {
        this.userName = userName;
    }

    public JpaUserDetails() {

    }
    /*
    // For now, hardcoding the username and password - will swap it with a JPA username & password lookup from the DB
    // Password encoded via a BCrypt Hash of plain text - using https://bcrypt-generator.com/
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        if ("drugabuse".equals(user)) {
            return new User("drugabuse", "$2y$12$jY6JNBkvEOQUtF/WEVJokOhvU21sqTBZ3kyF70GAUHS48u1zd0FjK",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found: " + user);
        }
    }
*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return "drugabuse";
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
