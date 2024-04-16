package com.example.ApiSporte.service;

import java.util.List;

import com.example.ApiSporte.exception.NotFoundException;
import com.example.ApiSporte.model.Enumerado;


public interface EnumeradoService {
    public Enumerado add (Enumerado enumerado);
    public Enumerado update (Enumerado enumerado);
    public boolean delete (Enumerado enumerado);
    public List<Enumerado> findAll();
    public Enumerado findById(int id) throws NotFoundException;
    public List<Enumerado> findEnumeradosHijos(int idPadre) throws NotFoundException;
}
