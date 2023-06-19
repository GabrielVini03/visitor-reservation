package visitorreservation.visitorreservationapi.controller.resources.interfaces.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.UpdateReservationDTO;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.UUID;

public interface UpdateReservationResourceInterface {

    ResponseEntity<ReservationDTO> update(@PathVariable UUID ReservationId, BindingResult error, @Valid @RequestBody UpdateReservationDTO updateReservationDTO) throws ValidationException, DataNotFoundException;
}
