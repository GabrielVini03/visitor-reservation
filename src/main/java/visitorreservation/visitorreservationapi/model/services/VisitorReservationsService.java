package visitorreservation.visitorreservationapi.model.services;

import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.CreateRequestVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.UpdateVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.Reservation;
import visitorreservation.visitorreservationapi.model.entities.Visitor;
import visitorreservation.visitorreservationapi.model.entities.VisitorReservation;
import visitorreservation.visitorreservationapi.model.mappers.ReservationsMapper;
import visitorreservation.visitorreservationapi.model.mappers.VisitorReservationsMapper;
import visitorreservation.visitorreservationapi.model.mappers.VisitorsMapper;
import visitorreservation.visitorreservationapi.model.repositories.ReservationsRepository;
import visitorreservation.visitorreservationapi.model.repositories.VisitorReservationsRepository;
import visitorreservation.visitorreservationapi.model.repositories.VisitorsRepository;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitorReservationsService {

    private final VisitorReservationsRepository visitorReservationsRepository;
    private final VisitorReservationsMapper visitorReservationsMapper;
    private final VisitorsRepository visitorsRepository;
    private final ReservationsRepository reservationsRepository;

    private final VisitorsMapper visitorsMapper;

    private final ReservationsMapper reservationsMapper;

    @Autowired
    public VisitorReservationsService(VisitorReservationsRepository visitorReservationsRepository,
                                     VisitorReservationsMapper visitorReservationsMapper,
                                     VisitorsRepository visitorsRepository,
                                     ReservationsRepository reservationsRepository, VisitorsMapper visitorsMapper, ReservationsMapper reservationsMapper) {
        this.visitorReservationsRepository = visitorReservationsRepository;
        this.visitorReservationsMapper = visitorReservationsMapper;
        this.visitorsRepository = visitorsRepository;
        this.reservationsRepository = reservationsRepository;
        this.visitorsMapper = visitorsMapper;
        this.reservationsMapper = reservationsMapper;
    }

    public VisitorReservation findByVisitorReservation(UUID visitorReservationId) {
        Optional<VisitorReservation> visitorReservation = visitorReservationsRepository.findById(visitorReservationId);

        if (visitorReservation.isEmpty()) throw new DataNotFoundException();

        return visitorReservation.get();
    }


    public VisitorReservationDTO insert(CreateRequestVisitorReservationDTO createVisitorReservationDTO) throws ConstraintViolationException, DataNotFoundException, ValidationException {
        Optional<Visitor> visitor = visitorsRepository.findById(createVisitorReservationDTO.getVisitorId());
        Optional<Reservation> reservation = reservationsRepository.findById(createVisitorReservationDTO.getReservationId());

        if (visitor.isEmpty()) {
            throw new DataNotFoundException("Visitor not found for ID: " + createVisitorReservationDTO.getVisitorId());
        }

        if (reservation.isEmpty()) {
            throw new DataNotFoundException("Reservation not found for ID: " + createVisitorReservationDTO.getReservationId());
        }

        VisitorReservationDTO visitorReservationDTO =  VisitorReservationDTO.builder()
                .reservationId(reservation.get().getId())
                .visitorId(visitor.get().getId())
                .build();

        VisitorReservation visitorReservation = visitorReservationsRepository.saveAndFlush(visitorReservationsMapper.mapFromVisitorReservationDTO(visitorReservationDTO));
        return visitorReservationsMapper.mapFromVisitorReservation(visitorReservation);

    }

    public VisitorReservationDTO update(UpdateVisitorReservationDTO updateVisitorReservationDTO, UUID visitorReservationId) throws DataNotFoundException  {
        VisitorReservation visitorReservation = findByVisitorReservation(visitorReservationId);

        Optional<Visitor> visitor = visitorsRepository.findById(updateVisitorReservationDTO.getVisitorID());
        Optional<Reservation> reservation = reservationsRepository.findById(updateVisitorReservationDTO.getVisitorID());

        visitorReservation.setVisitor(visitor.orElseThrow(() -> new DataNotFoundException("Visitor not found")));
        visitorReservation.setReservation(reservation.orElseThrow(() -> new DataNotFoundException("Reservation not found")));

        VisitorReservation updatedVisitorReservation = visitorReservationsRepository.save(visitorReservation);
        return visitorReservationsMapper.mapFromVisitorReservation(updatedVisitorReservation);

    }

    public VisitorReservationDTO find(UUID visitorReservationId) throws  DataNotFoundException {
        VisitorReservation visitorReservation = findByVisitorReservation(visitorReservationId);
        return visitorReservationsMapper.mapFromVisitorReservation(visitorReservation);
    }

    public Collection<VisitorReservationDTO> list() {
        Collection<VisitorReservation> visitorReservations = visitorReservationsRepository.findAll();
        return visitorReservationsMapper.mapFromVisitorReservationCollection(visitorReservations);
    }

    public boolean delete(UUID visitorReservationId) throws DataNotFoundException {
        if (visitorReservationsRepository.existsById(visitorReservationId))  {
            visitorReservationsRepository.deleteById(visitorReservationId);
            return true;
        }
        throw new DataNotFoundException("Visitor Reservation not found");
    }


}
