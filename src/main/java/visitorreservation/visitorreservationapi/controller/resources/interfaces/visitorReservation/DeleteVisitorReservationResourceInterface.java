package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitorReservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;

import java.util.UUID;

public interface DeleteVisitorReservationResourceInterface {

    ResponseEntity<String> delete(@PathVariable UUID VisitorReservationId) throws DataNotFoundException;
}
