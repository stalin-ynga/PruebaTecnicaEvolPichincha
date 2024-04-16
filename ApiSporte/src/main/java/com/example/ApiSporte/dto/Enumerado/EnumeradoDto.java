package com.example.ApiSporte.dto.Enumerado;

import lombok.Data;

@Data
public class EnumeradoDto {

    private Integer id;

    private int codigo;

    private String nombre;

    private String abreviatura;

    private String valor;
    
    private int orden;
}
