package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.VisitorDTO;
import java.util.UUID;

public interface FindVisitorResourceInterface {

    ResponseEntity<VisitorDTO> find(@PathVariable UUID visitorId) throws DataNotFoundException;
}
