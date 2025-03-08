package com.project.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModal{

    private String value;
    private Date expiryAt;

    @ManyToOne
    private User user;
}
