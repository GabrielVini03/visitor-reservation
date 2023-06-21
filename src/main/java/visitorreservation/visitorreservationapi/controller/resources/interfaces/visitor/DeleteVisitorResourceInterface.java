package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;

import java.util.UUID;

public interface DeleteVisitorResourceInterface {

    ResponseEntity<String> delete(@PathVariable UUID visitorId) throws DataNotFoundException;

}
