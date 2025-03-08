package com.project.userservice.services;

import com.project.userservice.exceptions.InvalidTokenException;
import com.project.userservice.models.Token;
import com.project.userservice.models.User;

import java.util.Optional;

public interface UserService {
     User signUp(String name,String email , String password);
     Token login(String email , String password);
     User logout(String tokenValue) throws InvalidTokenException;
     User validateToken(String tokenValue);
}
