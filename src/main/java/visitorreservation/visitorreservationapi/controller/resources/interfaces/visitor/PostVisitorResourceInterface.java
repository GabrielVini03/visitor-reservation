package visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.VisitorDTO;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

public interface PostVisitorResourceInterface {

    ResponseEntity<VisitorDTO> create(@Valid @RequestBody CreateVisitorRequestDTO createVisitorRequestDTO, BindingResult error) throws DataNotFoundException, ValidationException;
}
