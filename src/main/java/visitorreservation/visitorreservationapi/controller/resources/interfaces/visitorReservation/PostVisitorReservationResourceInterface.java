package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitorReservation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.CreateRequestVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.UpdateVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.UUID;

public interface PostVisitorReservationResourceInterface {

    ResponseEntity<VisitorReservationDTO> create(@Valid @RequestBody CreateRequestVisitorReservationDTO createRequestVisitorReservationDTO, BindingResult error) throws DataNotFoundException, javax.xml.bind.ValidationException;

}
