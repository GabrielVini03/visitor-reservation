package visitorreservation.visitorreservationapi.model.mappers;

import visitorreservation.visitorreservationapi.controller.DTO.domains.ReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.Reservation;
import visitorreservation.visitorreservationapi.model.mappers.commons.GenericMapper;

public interface ReservationsMapper extends GenericMapper<Reservation, ReservationDTO> {
}
