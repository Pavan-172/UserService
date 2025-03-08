package com.project.userservice.repositories;

import com.project.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Token save(Token token);

    Optional<Token>findTokenByValueAndDeletedAndExpiryAtGreaterThan(String value, boolean deleted, Date expiryAt);
}
