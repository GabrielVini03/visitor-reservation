package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitorReservation;

import org.springframework.http.ResponseEntity;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;

import java.util.Collection;

public interface ListVisitorReservationResourceInterface {

    ResponseEntity<Collection<VisitorReservationDTO>> list();
}
