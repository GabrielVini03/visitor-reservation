package visitorreservation.visitorreservationapi.model.mappers;

import org.mapstruct.*;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.CreateRequestVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.VisitorReservation;
import visitorreservation.visitorreservationapi.model.mappers.commons.IgnoreUnmappedConfig;

import java.util.Collection;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), config = IgnoreUnmappedConfig.class)
public interface VisitorReservationsMapper {

    @InheritInverseConfiguration(name = "mapFromVisitorReservationDTO")
    VisitorReservationDTO mapFromVisitorReservation(VisitorReservation visitorReservation);

    @InheritConfiguration
    VisitorReservation mapFromVisitorReservationDTO(VisitorReservationDTO visitorReservationDTO);

    Collection<VisitorReservationDTO> mapFromVisitorReservationCollection(Collection<VisitorReservation> visitorReservations);

    Collection<VisitorReservation> mapFromVisitorReservationDTOCollection(Collection<VisitorReservationDTO> visitorReservationDTOs);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVisitorReservationFromDTO(VisitorReservationDTO visitorReservationDTO, @MappingTarget VisitorReservation visitorReservation);

    VisitorReservation mapFromCreateDTO(CreateRequestVisitorReservationDTO createVisitorReservationDTO);
}



