package com.example.ApiSporte.dto.Exchange;

import java.sql.Date;

import lombok.Data;

@Data
public class ExchangeDto {
    private int id;

    public String nombreUsuario;

    public double montoInicial;

    public double montoFinal;

    public double tipoDeCambio;

    public int tipoMoneda;

    public Date fechaProceso;
}
