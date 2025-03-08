package com.project.userservice.exceptions;


import com.project.userservice.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDto> InvalidTokenException( InvalidTokenException invalidTokenException){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(invalidTokenException.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
    }

}
