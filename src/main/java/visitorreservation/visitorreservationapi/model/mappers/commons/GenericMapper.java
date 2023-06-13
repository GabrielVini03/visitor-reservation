package visitorreservation.visitorreservationapi.model.mappers.commons;


import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;
import org.mapstruct.*;

public interface GenericMapper<E, DTO> {

    E mapFromDTO(DTO source);

    DTO mapFromEntity(E source);

    Collection<DTO> mapFromEntityCollection(Collection<E> source);

    Collection<E> mapFromDTOCollection(Collection<DTO> source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(DTO dto, @MappingTarget E entity);
}
