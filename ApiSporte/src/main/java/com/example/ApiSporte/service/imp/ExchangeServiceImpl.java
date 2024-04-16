package com.example.ApiSporte.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiSporte.exception.NotFoundException;
import com.example.ApiSporte.model.ExChange;
import com.example.ApiSporte.repository.ExchangeRepository;
import com.example.ApiSporte.service.ExchangeService;
import java.util.*;

@Service
public class ExchangeServiceImpl implements ExchangeService{
    
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Override
    public ExChange add(ExChange exchange) {
        return exchangeRepository.save(exchange);
    }

    @Override
    public ExChange update(ExChange exchange){
        return exchangeRepository.save(exchange);
    }

    @Override
    public boolean delete(ExChange exchange){
        
        exchangeRepository.save(exchange);

        exchangeRepository.deleteById(exchange.getId());
        return true;
    }

    @Override
    public List<ExChange> findAll(){
        return(List<ExChange>) exchangeRepository.findAll();
    }

    
    @Override
    public ExChange findById(int id) throws NotFoundException{
        Optional<ExChange> model = exchangeRepository.findById(id);

        if(model.isEmpty() || model.get().isEsEliminado()){
            throw new NotFoundException("No se encontro el exchange");
        }
       
        return model.get();
    }

}
