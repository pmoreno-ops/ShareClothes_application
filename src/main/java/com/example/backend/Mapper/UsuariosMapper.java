package com.example.backend.Mapper;

import com.example.backend.DTO.UsuarioDTO;
import com.example.backend.models.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    UsuariosMapper INSTANCE = Mappers.getMapper(UsuariosMapper.class);

    // Entity -> DTO
    UsuarioDTO toDTO(Usuarios usuario);

    // DTO -> Entity
    Usuarios toEntity(UsuarioDTO usuarioDTO);
}
