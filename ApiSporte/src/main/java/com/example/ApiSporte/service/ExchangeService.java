package com.example.ApiSporte.service;
import java.util.*;

import com.example.ApiSporte.exception.NotFoundException;
import com.example.ApiSporte.model.ExChange;

public interface ExchangeService {
    public ExChange add (ExChange enumerado);
    public ExChange update (ExChange enumerado);
    public boolean delete (ExChange enumerado);
    public List<ExChange> findAll();
    public ExChange findById(int id) throws NotFoundException;
}
