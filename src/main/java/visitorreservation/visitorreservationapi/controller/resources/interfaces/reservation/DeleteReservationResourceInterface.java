package visitorreservation.visitorreservationapi.controller.resources.interfaces.reservation;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;

import java.util.UUID;

public interface DeleteReservationResourceInterface {

    ResponseEntity<String> delete(@PathVariable UUID ReservationId) throws DataNotFoundException;

}
