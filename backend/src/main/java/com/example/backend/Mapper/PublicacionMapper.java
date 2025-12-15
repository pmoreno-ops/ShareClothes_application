package com.example.backend.Mapper;

import com.example.backend.DTO.PublicacionDTO;
import com.example.backend.models.Publicacion;
import com.example.backend.models.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PublicacionMapper {

    @Mapping(target = "productoId", source = "producto.id")
    @Mapping(target = "usuarioId", source = "usuariosFavoritos", qualifiedByName = "mapUsuarioId")
    @Mapping(target = "producto", ignore = true) // Ignorado si no usas ProductoDTO
    @Mapping(target = "fechaPublicacion", expression = "java(publicacion.getFechaPublicacion() != null ? publicacion.getFechaPublicacion().toString() : null)")
    PublicacionDTO toDTO(Publicacion publicacion);

    @Mapping(target = "producto.id", source = "productoId")
    @Mapping(target = "usuariosFavoritos", ignore = true) // Se maneja en otro lugar
    @Mapping(target = "fechaPublicacion", ignore = true)
    Publicacion toEntity(PublicacionDTO dto);

    // Método para obtener un usuarioId del Set<Usuarios>
    @Named("mapUsuarioId")
    default Long mapUsuarioId(Set<Usuarios> usuarios) {
        if (usuarios == null || usuarios.isEmpty()) {
            return null;
        }
        // Retornamos el id del primer usuario del set
        return usuarios.iterator().next().getId();
    }
}
