package com.project.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.userservice.models.Role;
import com.project.userservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {

    public CustomUserDetails(){};
    String username;
    String password;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;

    List<CustomGrantedAuthority> authorities ;


    public CustomUserDetails(User user){
//    this.user =user;
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.accountNonLocked =true;
        this.accountNonExpired = true;
        this.enabled = true;
        this.credentialsNonExpired = true;
        List<Role> roles= user.getRoles();

        for(Role r : roles){
            authorities.add(new CustomGrantedAuthority(r));
        }

    }
    @Override
//    ? expects a class with extends or implements GrantedAuthority
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {

//        jackson wont allow doing this
//        return user.getPassword();
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
