package com.example.Experiencia.dto;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ApiResponse<T> {

    @Getter
    private final int status;

    @Getter
    private final String mensaje;

    @Getter
    private final T data;
    
    @Getter
    private final List<String> mensajes;

    

}
