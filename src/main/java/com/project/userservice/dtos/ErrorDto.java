package com.project.userservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorDto {
    private String message;
    private String status;
}
