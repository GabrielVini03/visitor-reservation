package visitorreservation.visitorreservationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.UpdateVisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;
import visitorreservation.visitorreservationapi.controller.resources.interfaces.visitor.VisitorControllerInterface;
import visitorreservation.visitorreservationapi.model.services.VisitorsService;

import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/visitors")
public class VisitorsController implements VisitorControllerInterface {


    private VisitorsService visitorsService;

    @Autowired
    public void VisitorsService(VisitorsService visitorsService){
        this.visitorsService = visitorsService;
    }
    @Override
    @PostMapping
    public ResponseEntity<VisitorDTO> create(CreateVisitorRequestDTO createVisitorRequestDTO, BindingResult error) throws DataNotFoundException, ValidationException {
        if (error.hasErrors()) {
            throw new ValidationException(Objects.nonNull(error.getFieldError()) ? error.getFieldError().getDefaultMessage() : "Validation error");
        }

        VisitorDTO visitorDTO = visitorsService.insert(createVisitorRequestDTO);
        return ResponseEntity.ok(visitorDTO);
    }


    @Override
    @PutMapping("/{visitorId}")
    public ResponseEntity<VisitorDTO> update(UpdateVisitorDTO updateVisitorDTO, UUID visitorId, BindingResult error) throws Exception {
        if (error.hasErrors()) {
            throw new ValidationException(Objects.nonNull(error.getFieldError()) ? error.getFieldError().getDefaultMessage() : "Validation error");
        }

        VisitorDTO visitorDTO = visitorsService.update(updateVisitorDTO, visitorId);
        return ResponseEntity.ok(visitorDTO);
    }


    @Override
    @DeleteMapping("/{visitorId}")
    public ResponseEntity<String> delete(UUID visitorId) throws DataNotFoundException {
       visitorsService.delete(visitorId);
        return new ResponseEntity<String>("Excluído com sucesso", HttpStatus.OK);
    }

    @Override
    @GetMapping("/{visitorId}")
    public ResponseEntity<VisitorDTO> find(UUID visitorId) throws DataNotFoundException {
       VisitorDTO visitorDTO = visitorsService.find(visitorId);

       return new ResponseEntity<VisitorDTO>(visitorDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<VisitorDTO>> list() {
        Collection<VisitorDTO> visitorDTOS = visitorsService.list();

        return ResponseEntity.ok().body(visitorDTOS);
    }
}



