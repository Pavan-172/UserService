package com.project.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    public CustomGrantedAuthority(){};

    String rolename;

    public CustomGrantedAuthority(Role role){
        this.rolename = role.getValue();
    }
    @Override
    public String getAuthority() {
        return rolename;
    }
}
