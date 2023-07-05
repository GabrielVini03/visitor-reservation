package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitorReservation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.UpdateVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.UUID;

public interface UpdateVisitorReservationResourceInterface {


    ResponseEntity<VisitorReservationDTO> update(@Valid @RequestBody UpdateVisitorReservationDTO updateVisitorReservationDTO, @PathVariable UUID ReservationId, BindingResult error) throws ValidationException, DataNotFoundException;
}
