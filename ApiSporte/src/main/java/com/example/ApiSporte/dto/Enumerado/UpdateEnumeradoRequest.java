package com.example.ApiSporte.dto.Enumerado;

import com.example.ApiSporte.model.Enumerado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEnumeradoRequest {
    private int id;

    private int idPadre;

    private int codigo;

    @NotBlank(message = "Por favor agregue el nombre")
    private String nombre;

    private String abreviatura;

    private String valor;

    private int orden;

    @JsonIgnore
    private Enumerado padre;
}
