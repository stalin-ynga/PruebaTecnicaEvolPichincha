package com.example.ApiSporte.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.example.ApiSporte.dto.Exchange.CreateExchangeRequest;
import com.example.ApiSporte.dto.Exchange.ExchangeDto;
import com.example.ApiSporte.dto.Exchange.UpdateExchangeRequest;
import com.example.ApiSporte.model.ExChange;

@Mapper(componentModel = "spring")
public interface ExchangeMapper {

    ExchangeDto toDto(ExChange exChange);


    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "esEliminado", constant = "false"),
        @Mapping(target = "fechaCreacion", expression = "java(new java.util.Date())"),
        @Mapping(target = "fechaModificacion", ignore = true),
        @Mapping(target = "usuarioModificacion", ignore = true),
    })
    ExChange createRequestToEntity(CreateExchangeRequest createEnumeradoRequest);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "esEliminado", ignore = true),
        @Mapping(target = "usuarioCreacion", ignore = true),
        @Mapping(target = "fechaCreacion", ignore = true),
        @Mapping(target = "fechaModificacion", expression = "java(new java.util.Date())"),
    })
    void updateRequestToEntity(@MappingTarget ExChange entity,UpdateExchangeRequest updateEnumeradoRequest);
}
