package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;

import org.springframework.http.ResponseEntity;
import visitorreservation.visitorreservationapi.controller.DTO.domains.VisitorDTO;

import java.util.Collection;

public interface ListVisitorResourceInterface {

    ResponseEntity<Collection<VisitorDTO>> list();
}
