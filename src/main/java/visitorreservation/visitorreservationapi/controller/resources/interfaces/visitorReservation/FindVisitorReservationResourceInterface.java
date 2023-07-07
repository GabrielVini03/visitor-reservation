package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitorReservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;

import java.util.UUID;

public interface FindVisitorReservationResourceInterface {

    ResponseEntity<VisitorReservationDTO> find(@PathVariable UUID reservationId) throws DataNotFoundException;
}
