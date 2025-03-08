package com.project.userservice.controllers;

import com.project.userservice.dtos.LoginRequestDto;
import com.project.userservice.dtos.LoginResponseDto;
import com.project.userservice.dtos.SignupRequestDto;
import com.project.userservice.dtos.UserDto;
import com.project.userservice.exceptions.InvalidTokenException;
import com.project.userservice.models.Token;
import com.project.userservice.models.User;
import com.project.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
     UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public UserDto signUp(@RequestBody SignupRequestDto signupRequestDto  ){

        User user = userService.signUp(signupRequestDto.getName(),signupRequestDto.getEmail(),signupRequestDto.getPassword());

        return UserDto.from(user);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){

        Token token = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setTokenValue(token.getValue());
        return loginResponseDto;

    }
    @GetMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token) throws InvalidTokenException {
        User user = userService.logout(token);
        ResponseEntity<Void> responseEntity;
        if(user == null){
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }
        return responseEntity;
    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable("token") String tokenValue){

        User user = userService.validateToken(tokenValue);
        ResponseEntity<Boolean> responseEntity;

        if(user == null){
            responseEntity = new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }else{
            responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
        }

        return responseEntity;
    }
}
