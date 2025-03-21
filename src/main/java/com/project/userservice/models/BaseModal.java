package com.project.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModifiedAt;
    private boolean deleted;
}
