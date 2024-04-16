package com.example.ApiSporte.model;

import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Exchange")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Exchange SET ES_ELIMINADO = 1 WHERE ID = ?")
@SQLRestriction(value = "ES_ELIMINADO = 0")
public class ExChange {
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombreUsuario", nullable = false, length = 50)
    public String nombreUsuario;

    @Column(name = "montoInicial", nullable = false)
    public double montoInicial;

    @Column(name = "montoFinal", nullable = false)
    public double montoFinal;

    @Column(name = "tipoDeCambio", nullable = false)
    public double tipoDeCambio;

    @Column(name = "tipoMoneda", nullable = false)
    public int tipoMoneda;

    @Column(name = "fechaProceso", nullable = false)
    public Date fechaProceso;

    @Column(name = "esEliminado", nullable = false)
    private boolean esEliminado;

    @Column(name = "fechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "usuarioCreacion", nullable = false)
    private int usuarioCreacion;

    @Column(name = "fechaModificacion", nullable = true)
    private Date fechaModificacion;

    @Column(name = "usuarioModificacion", nullable = true)
    private int usuarioModificacion;

}
