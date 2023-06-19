package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.UpdateVisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.VisitorDTO;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.UUID;

public interface UpdateVisitorResourceInterface {

    ResponseEntity<VisitorDTO> update(@PathVariable UUID visitorId, BindingResult error, @Valid @RequestBody UpdateVisitorDTO updateVisitorDTO) throws ValidationException, DataNotFoundException;
}
