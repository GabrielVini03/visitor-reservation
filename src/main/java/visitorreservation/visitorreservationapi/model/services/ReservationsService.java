package visitorreservation.visitorreservationapi.model.services;

import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.UpdateReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.Reservation;
import visitorreservation.visitorreservationapi.model.entities.Visitor;
import visitorreservation.visitorreservationapi.model.mappers.ReservationsMapper;
import visitorreservation.visitorreservationapi.model.mappers.VisitorsMapper;
import visitorreservation.visitorreservationapi.model.repositories.ReservationsRepository;
import visitorreservation.visitorreservationapi.model.repositories.VisitorsRepository;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@Service
public class ReservationsService {

    ReservationsRepository reservationsRepository;
    ReservationsMapper reservationsMapper;

    VisitorsRepository visitorsRepository;

    VisitorsMapper visitorsMapper;

    @Autowired
    public void ReservationsService(ReservationsRepository reservationsRepository, ReservationsMapper reservationsMapper, VisitorsRepository visitorsRepository, VisitorsMapper visitorsMapper) {
        this.reservationsMapper = reservationsMapper;
        this.reservationsRepository = reservationsRepository;
        this.visitorsRepository = visitorsRepository;
        this.visitorsMapper = visitorsMapper;
    }

    public Reservation findByReservation(UUID reservationId) {
        Optional<Reservation> reservation = reservationsRepository.findById(reservationId);

        if (reservation.isEmpty()) throw new DataNotFoundException();

        return reservation.get();
    }

    public ReservationDTO insert(CreateReservationRequestDTO createdReservationDTO) throws ConstraintViolationException, DataNotFoundException, ValidationException {
        Optional<Visitor> visitor = visitorsRepository.findById(createdReservationDTO.getVisitorId());

        if (visitor.isEmpty()) throw new DataNotFoundException("Visitor not found");

        LocalDateTime date = createdReservationDTO.getReservationDate();
        LocalDateTime lowerDateTime = LocalDateTime.of(date.toLocalDate(), LocalTime.of(date.getHour(), 0, 0));
        LocalDateTime upperDateTime = lowerDateTime.plusHours(1);

        boolean isAvailableReservationDate = reservationsRepository.findByReservationDate(upperDateTime, lowerDateTime).isEmpty();

        if (!isAvailableReservationDate) {
            throw new DataNotFoundException("There is already a reservation for this visitor at this time.");
        }


        ReservationDTO reservationDTO = ReservationDTO.builder()
                .visitorId(visitor.get().getId())
                .reservationDate(createdReservationDTO.getReservationDate())
                .build();

        Reservation reservation = reservationsRepository.saveAndFlush(reservationsMapper.mapFromReservationDTO(reservationDTO));
        return reservationsMapper.mapFromReservation(reservation);
    }

    public ReservationDTO update(UpdateReservationDTO updateReservationDTO, UUID reservationId) throws DataNotFoundException {

        Reservation reservation = this.findByReservation(reservationId);

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .visitorId(reservation.getVisitor().getId())
                .reservationDate(reservation.getReservationDate())
                .build();

        if (Objects.nonNull(updateReservationDTO.getVisitorId())) {

            Optional<Visitor> visitor = visitorsRepository.findById(updateReservationDTO.getVisitorId());
            if (visitor.isEmpty()) throw new DataNotFoundException("Visitor not found");

            reservationDTO.setVisitorId(visitor.get().getId());
        }

        if (Objects.nonNull(updateReservationDTO.getReservationDate())) {
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH");

            LocalDateTime date = updateReservationDTO.getReservationDate();
            LocalDateTime lowerDateTime = LocalDateTime.of(
                    date.toLocalDate(), LocalTime.of(date.getHour(), 0, 0));
            LocalDateTime upperDateTime = lowerDateTime.plusHours(1);

            reservationDTO.setReservationDate(lowerDateTime);

            boolean visitorIdIsEquals = Objects.isNull(updateReservationDTO.getVisitorId()) ||
                    reservationDTO.getVisitorId().equals(updateReservationDTO.getVisitorId());

            boolean isTimeAvailable = reservationsRepository.findByReservationDate(upperDateTime, lowerDateTime).isEmpty();

            if (!isTimeAvailable) {

                if (!(visitorIdIsEquals && reservation.getReservationDate().equals(updateReservationDTO.getReservationDate())))
                    throw new DataNotFoundException("There is already a reservation for this visitor at this time.");
            }
        }

        reservationsMapper.updateReservationFromReservationDTO(reservationDTO, reservation);

        return reservationsMapper.mapFromReservation(reservationsRepository.saveAndFlush(reservation));

    }

    public ReservationDTO find(UUID reservationId) throws DataNotFoundException {
        return reservationsMapper.mapFromReservation(this.findByReservation(reservationId));
    }

    public Collection<ReservationDTO> list() {
        return reservationsMapper.mapFromReservationCollection(reservationsRepository.findAll());
    }

    public boolean delete(UUID reservationId) throws DataNotFoundException {
        Reservation reservation = this.findByReservation(reservationId);

        reservationsRepository.delete(reservation);

        return true;
    }


}
