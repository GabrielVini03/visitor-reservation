package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.UpdateVisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;

import javax.validation.Valid;
import java.util.UUID;

public interface UpdateVisitorResourceInterface {

    ResponseEntity<VisitorDTO> update(@Valid @RequestBody UpdateVisitorDTO updateVisitorDTO, @PathVariable UUID visitorId, BindingResult error) throws Exception;
}
