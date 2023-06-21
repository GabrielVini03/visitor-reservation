package visitorreservation.visitorreservationapi.model.mappers;

import org.mapstruct.*;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;
import visitorreservation.visitorreservationapi.model.entities.Visitor;
import visitorreservation.visitorreservationapi.model.mappers.commons.IgnoreUnmappedConfig;

import java.util.Collection;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), config = IgnoreUnmappedConfig.class)
public interface VisitorsMapper {

    Visitor mapFromVisitorDTO(VisitorDTO visitorDTO);

    VisitorDTO mapFromVisitor(Visitor visitor);

    Collection<VisitorDTO> mapFromVisitorCollection(Collection<Visitor> source);

    Collection<Visitor> mapFromVisitorDTOCollection(Collection<VisitorDTO> source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVisitorFromVisitorDTO(VisitorDTO visitorDTO, @MappingTarget Visitor visitor);
}
