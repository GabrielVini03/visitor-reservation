package visitorreservation.visitorreservationapi.controller.resources.interfaces.reservation;

import org.springframework.http.ResponseEntity;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;

import java.util.Collection;

public interface ListReservationResourceInterface {

    ResponseEntity<Collection<ReservationDTO>> list();
}
