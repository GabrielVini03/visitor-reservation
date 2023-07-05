package visitorreservation.visitorreservationapi.controller.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.UpdateReservationDTO;
import visitorreservation.visitorreservationapi.controller.resources.interfaces.reservation.ReservationControllerInterface;
import visitorreservation.visitorreservationapi.model.services.ReservationsService;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController implements ReservationControllerInterface {

    private ReservationsService reservationsService;

    @Autowired
    public void ReservationController(ReservationsService reservationsService){
        this.reservationsService = reservationsService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ReservationDTO> create(CreateReservationRequestDTO createReservationRequestDTO, BindingResult error) throws DataNotFoundException, ValidationException {
        if (error.hasErrors()) {
            throw new ValidationException(Objects.nonNull(error.getFieldError()) ? error.getFieldError().getDefaultMessage() : "Validation error");
        }

        ReservationDTO reservationDTO = reservationsService.insert(createReservationRequestDTO);
        return ResponseEntity.ok(reservationDTO);
    }


    @Override
    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> update(UpdateReservationDTO updateReservationDTO, UUID reservationId, BindingResult error) throws ValidationException, DataNotFoundException {
        if (error.hasErrors()) {
            throw new ValidationException(Objects.nonNull(error.getFieldError()) ? error.getFieldError().getDefaultMessage() : "Validation error");
        }

        ReservationDTO reservationDTO = reservationsService.update(updateReservationDTO, reservationId);
        return ResponseEntity.ok(reservationDTO);
    }


    @Override
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> delete(UUID reservationId) throws DataNotFoundException {
        reservationsService.delete(reservationId);
        return new ResponseEntity<String>("Excluído com sucesso", HttpStatus.OK);
    }

    @Override
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> find(UUID reservationId) throws DataNotFoundException {
        ReservationDTO reservationDTO = reservationsService.find(reservationId);

        return new ResponseEntity<ReservationDTO>(reservationDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<ReservationDTO>> list() {
        Collection<ReservationDTO> reservationDTOS = reservationsService.list();

        return ResponseEntity.ok().body(reservationDTOS);
    }

}



