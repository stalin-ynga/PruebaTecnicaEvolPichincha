package com.example.ApiSporte.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.ApiSporte.dto.Enumerado.CreateEnumeradoRequest;
import com.example.ApiSporte.dto.Enumerado.EnumeradoDto;
import com.example.ApiSporte.dto.Enumerado.UpdateEnumeradoRequest;
import com.example.ApiSporte.exception.NotFoundException;
import com.example.ApiSporte.mapper.EnumeradoMapper;
import com.example.ApiSporte.model.Enumerado;
import com.example.ApiSporte.service.EnumeradoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/enumerado")
@Tag(name = "Enumerado")
public class EnumeradoController {

    @Autowired
    EnumeradoService enumeradoService;

    @Autowired
    EnumeradoMapper enumeradoMapper;

    @GetMapping("listar-enumerados")
    public ResponseEntity<ApiResponse<?>> getEnumerados() {
        try {
            List<Enumerado> enumerados = enumeradoService.findAll();
            List<EnumeradoDto> enumeradosDto = enumerados.stream()
                    .map(enumeradoMapper::toDto)
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
        
        Enumerado enumerado = enumeradoService.findById(id);

        EnumeradoDto enumeradoDto = enumeradoMapper.toDto(enumerado);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                "", enumeradoDto, Collections.emptyList()));
        
    }

    @GetMapping("/listar-enumerados-padre/{id}")
    public ResponseEntity<ApiResponse<?>> getEnumeradosByPadre(@PathVariable int id) throws NotFoundException {
        
            List<Enumerado> enumerados = enumeradoService.findEnumeradosHijos(id);

            List<EnumeradoDto> enumeradosDto = enumerados.stream()
                                .map(enumeradoMapper::toDto)
                                .collect(Collectors.toList());

            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                    "", enumeradosDto, Collections.emptyList()));
       
        
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<?>> createEnumerado(@Valid @RequestBody CreateEnumeradoRequest request) throws NotFoundException {

            if(request.getIdPadre() > 0){
                Enumerado enumeradoPadre = enumeradoService.findById(request.getIdPadre());

                request.setPadre(enumeradoPadre);
            }
            
            Enumerado enumerado = enumeradoMapper.createRequestToEntity(request);

            enumeradoService.add(enumerado);

            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                                                        MensajesParametrizados.MENSAJE_CREAR_EXITOSO, null,Collections.emptyList()));
        
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ApiResponse<?>> update(@Valid @RequestBody UpdateEnumeradoRequest request) throws NotFoundException{
        
        Enumerado enumerado = enumeradoService.findById(request.getId());

        if(enumerado.getPadre() != request.getPadre()){
            Enumerado enumeradoPadre = enumeradoService.findById(request.getIdPadre());

            request.setPadre(enumeradoPadre);
        }

        enumeradoMapper.updateRequestToEntity(enumerado,request);

        enumeradoService.update(enumerado);

        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                                                    MensajesParametrizados.MENSAJE_EDITADO_EXITOSO, null, Collections.emptyList() ));
        
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable int id)  throws NotFoundException{
        
            Enumerado enumerado = enumeradoService.findById(id);
                    
            enumeradoService.delete(enumerado);

            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),
                MensajesParametrizados.MENSAJE_ELIMINAR_EXITOSO, null,Collections.emptyList()));

        
    }

}
