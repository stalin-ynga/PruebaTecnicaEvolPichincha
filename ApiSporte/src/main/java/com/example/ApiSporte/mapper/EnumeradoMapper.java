package com.example.ApiSporte.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.example.ApiSporte.dto.Enumerado.CreateEnumeradoRequest;
import com.example.ApiSporte.dto.Enumerado.EnumeradoDto;
import com.example.ApiSporte.dto.Enumerado.UpdateEnumeradoRequest;
import com.example.ApiSporte.model.Enumerado;


@Mapper(componentModel = "spring")
public interface EnumeradoMapper {
    
    
    EnumeradoDto toDto(Enumerado enumerado);


    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "esEliminado", constant = "false"),
    })
    Enumerado createRequestToEntity(CreateEnumeradoRequest createEnumeradoRequest);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "esEliminado", ignore = true),
    })
    void updateRequestToEntity(@MappingTarget Enumerado entity,UpdateEnumeradoRequest updateEnumeradoRequest);

}
