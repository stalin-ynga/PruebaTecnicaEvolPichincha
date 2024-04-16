package com.example.Experiencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Experiencia.constants.MensajesParametrizados;
import com.example.Experiencia.dto.ApiResponse;
import com.example.Experiencia.dto.Auth.LoginRequest;
import com.example.Experiencia.dto.Auth.LoginResponse;
import com.example.Experiencia.exception.NotFoundException;
import com.example.Experiencia.security.JwtToken;

import java.util.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth")
public class AuthController {

    @Autowired
    private JwtToken jwtToken;

    private WebClient webClient;
    
    public AuthController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://gorest.co.in/public/v2").build();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> createEnumerado(@Valid @RequestBody LoginRequest request) throws NotFoundException {

            try {
                List<LoginResponse> users = webClient.get().uri("/users").retrieve().bodyToFlux(LoginResponse.class)
                                                                                                    .collectList()
                                                                                                    .block();
                
                for (LoginResponse user : users) {
                    if(user.getEmail().equals(request.getEmail()) && user.getStatus().equals("active")){

                        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
                            "", jwtToken.generateToken(user),Collections.emptyList()));
                    }
                }

                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),
                "Usuario no encontrado", null,Collections.emptyList()));

            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null,Collections.emptyList()));
            }

            

            
        
    }

}
