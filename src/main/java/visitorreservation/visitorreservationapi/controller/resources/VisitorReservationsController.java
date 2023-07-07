package visitorreservation.visitorreservationapi.controller.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.CreateRequestVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.resources.interfaces.visitorReservation.VisitorReservationControllerInterface;
import visitorreservation.visitorreservationapi.model.services.VisitorReservationsService;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/visitorReservations")
@CrossOrigin(origins = "http://127.0.0.1:5173")
public class VisitorReservationsController implements VisitorReservationControllerInterface {

    private  VisitorReservationsService visitorReservationsService;

    @Autowired
    public void VisitorReservationsController(VisitorReservationsService visitorReservationsService){
        this.visitorReservationsService = visitorReservationsService;
    }

    @Override
    @PostMapping
    public ResponseEntity<VisitorReservationDTO> create(CreateRequestVisitorReservationDTO createVisitorReservationRequestDTO, BindingResult error) throws DataNotFoundException, ValidationException {
        if (error.hasErrors()) {
            throw new ValidationException(Objects.nonNull(error.getFieldError()) ? error.getFieldError().getDefaultMessage() : "Validation error");
        }

        VisitorReservationDTO visitorReservationDTO = visitorReservationsService.insert(createVisitorReservationRequestDTO);
        return ResponseEntity.ok(visitorReservationDTO);
    }

    @Override
    @DeleteMapping("/{visitorReservationId}")
    public ResponseEntity<String> delete(@PathVariable UUID reservationId) throws DataNotFoundException {
        visitorReservationsService.delete(reservationId);
        return ResponseEntity.ok("Visitor reservation deleted successfully.");
    }

    @GetMapping
    public ResponseEntity<Collection<VisitorReservationDTO>> list() {
        Collection<VisitorReservationDTO> visitorReservationDTOs = visitorReservationsService.list();

        return ResponseEntity.ok().body(visitorReservationDTOs);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<VisitorReservationDTO> find(UUID reservationId) throws DataNotFoundException {

        VisitorReservationDTO visitorReservationDTO = visitorReservationsService.find(reservationId);
        return ResponseEntity.ok().body(visitorReservationDTO);
    }
}
