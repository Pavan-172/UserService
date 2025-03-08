package com.project.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModal{
    private String name;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;
}
