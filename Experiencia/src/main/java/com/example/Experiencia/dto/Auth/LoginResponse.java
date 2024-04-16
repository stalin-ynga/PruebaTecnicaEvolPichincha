package com.example.Experiencia.dto.Auth;

import lombok.Data;

@Data
public class LoginResponse {
    long id;
    String name;
    String email;
    String gender;
    String status;
}
