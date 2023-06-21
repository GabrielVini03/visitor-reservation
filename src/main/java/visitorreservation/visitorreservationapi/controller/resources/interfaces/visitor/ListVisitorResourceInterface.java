package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;

import org.springframework.http.ResponseEntity;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;

import java.util.Collection;

public interface ListVisitorResourceInterface {

    ResponseEntity<Collection<VisitorDTO>> list();
}
