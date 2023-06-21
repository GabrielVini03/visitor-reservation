package visitorreservation.visitorreservationapi.model.mappers;

import org.mapstruct.*;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.Reservation;
import visitorreservation.visitorreservationapi.model.mappers.commons.IgnoreUnmappedConfig;

import java.util.Collection;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), config = IgnoreUnmappedConfig.class)
public interface ReservationsMapper {

    @Mapping(source = "visitorId", target = "visitor.id")
    Reservation mapFromReservationDTO(ReservationDTO reservationDTO);

    @InheritInverseConfiguration(name = "mapFromReservationDTO")
    ReservationDTO mapFromReservation(Reservation reservation);

    Collection<ReservationDTO> mapFromReservationCollection(Collection<Reservation> source);

    Collection<Reservation> mapFromReservationDTOCollection(Collection<ReservationDTO> source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReservationFromReservationDTO(ReservationDTO reservationDTO, @MappingTarget Reservation reservation);
}

