package visitorreservation.visitorreservationapi.controller.resources.interfaces.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.ReservationDTO;

import java.util.UUID;

public interface FindReservationResourceInterface {

    ResponseEntity<ReservationDTO> find(@PathVariable UUID reservation) throws DataNotFoundException;
}
