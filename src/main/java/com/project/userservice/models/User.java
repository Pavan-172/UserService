package com.project.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModal{
    private String name;
    private String email;
    private String password;
// List is lazy loaded by default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
