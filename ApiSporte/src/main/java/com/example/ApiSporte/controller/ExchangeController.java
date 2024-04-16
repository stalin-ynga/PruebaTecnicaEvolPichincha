package com.example.ApiSporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiSporte.constants.MensajesParametrizados;
import com.example.ApiSporte.dto.ApiResponse;
import com.example.ApiSporte.dto.Exchange.CreateExchangeRequest;
import com.example.ApiSporte.dto.Exchange.ExchangeDto;
import com.example.ApiSporte.dto.Exchange.UpdateExchangeRequest;
import com.example.ApiSporte.exception.NotFoundException;
import com.example.ApiSporte.mapper.ExchangeMapper;
import com.example.ApiSporte.model.ExChange;
import com.example.ApiSporte.service.ExchangeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/exchange")
@Tag(name = "Exchange")
public class ExchangeController {
    
    @Autowired
    ExchangeService exchangeService;

    @Autowired
    ExchangeMapper exchangeMapper;

    @GetMapping("listar-exchange")
    public ResponseEntity<ApiResponse<?>> getEnumerados() {
        try {
            List<ExChange> enumerados = exchangeService.findAll();
            List<ExchangeDto> enumeradosDto = enumerados.stream()
                    .map(exchangeMapper::toDto)
                    .collect(Collectors.toList());

         
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                    "", enumeradosDto, Collections.emptyList()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            MensajesParametrizados.MENSAJE_ERROR_INTERNO_SERVIDOR, null,Collections.emptyList()));
        }
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ApiResponse<?>> findSolicitudById(@PathVariable int id) throws NotFoundException{
        
        ExChange exchange = exchangeService.findById(id);

        ExchangeDto exchangeDto = exchangeMapper.toDto(exchange);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "", exchangeDto, Collections.emptyList()));
        
    }


    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<?>> createEnumerado(@Valid @RequestBody CreateExchangeRequest request) throws NotFoundException {

            

            ExChange exChange = exchangeMapper.createRequestToEntity(request);

            exchangeService.add(exChange);

            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                                                        MensajesParametrizados.MENSAJE_CREAR_EXITOSO, null,Collections.emptyList()));
        
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<?>> update(@Valid @RequestBody UpdateExchangeRequest request) throws NotFoundException{
        
        ExChange exChange = exchangeService.findById(request.getId());


        exchangeMapper.updateRequestToEntity(exChange,request);

        exchangeService.update(exChange);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                                                    MensajesParametrizados.MENSAJE_EDITADO_EXITOSO, null, Collections.emptyList() ));
        
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable int id)  throws NotFoundException{
        
            ExChange exChange = exchangeService.findById(id);
                    
            exchangeService.delete(exChange);

            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                MensajesParametrizados.MENSAJE_ELIMINAR_EXITOSO, null,Collections.emptyList()));

        
    }
}
