package com.project.userservice.repositories;

import com.project.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   User save(User user);

   Optional<User> findByEmail(String email);
}
