package visitorreservation.visitorreservationapi.model.mappers;

import org.mapstruct.*;
import visitorreservation.visitorreservationapi.controller.DTO.domains.ReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.Reservation;
import visitorreservation.visitorreservationapi.model.mappers.commons.IgnoreUnmappedConfig;

import java.util.Collection;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), config = IgnoreUnmappedConfig.class)
public interface ReservationsMapper {


    Reservation mapFromReservationDTO(ReservationDTO reservationDTO);

    ReservationDTO mapFromReservation(Reservation reservationDTO);

    Collection<ReservationDTO> mapFromReservationCollection(Collection<Reservation> source);

    Collection<Reservation> mapFromReservationDTOCollection(Collection<ReservationDTO> source);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReservationFromReservationDTO(ReservationDTO reservationDTO, @MappingTarget Reservation reservation);
}
