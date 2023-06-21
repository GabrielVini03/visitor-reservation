package visitorreservation.visitorreservationapi.controller.resources.interfaces.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;


import javax.validation.Valid;
import javax.xml.bind.ValidationException;

public interface PostReservationResourceInterface {

    ResponseEntity<ReservationDTO> create(@Valid @RequestBody CreateReservationRequestDTO createReservationRequestDTO, BindingResult error) throws DataNotFoundException, ValidationException;
}
