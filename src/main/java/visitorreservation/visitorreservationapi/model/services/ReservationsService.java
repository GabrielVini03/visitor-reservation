package visitorreservation.visitorreservationapi.model.services;

import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.UpdateReservationDTO;
import visitorreservation.visitorreservationapi.model.entities.Reservation;
import visitorreservation.visitorreservationapi.model.mappers.ReservationsMapper;
import visitorreservation.visitorreservationapi.model.repositories.ReservationsRepository;

import javax.validation.ValidationException;
import java.util.*;
@Service
public class ReservationsService {

    ReservationsRepository reservationsRepository;

    ReservationsMapper reservationsMapper;

    @Autowired
    public void ReservationsService(ReservationsRepository reservationsRepository, ReservationsMapper reservationsMapper){
        this.reservationsMapper = reservationsMapper;
        this.reservationsRepository = reservationsRepository;
    }

    public Reservation findByReservation(UUID reservationId){
        Optional<Reservation> reservation = reservationsRepository.findById(reservationId);

        if (reservation.isEmpty()) throw new DataNotFoundException();

        return reservation.get();
    }
    public ReservationDTO insert(ReservationDTO reservationDTO) throws ConstraintViolationException, DataNotFoundException, ValidationException {
        Reservation reservation = reservationsRepository.saveAndFlush(reservationsMapper.mapFromReservationDTO(reservationDTO));

        return reservationsMapper.mapFromReservation(reservation);
    }

    public ReservationDTO update(UpdateReservationDTO UpdateReservationDTO, UUID reservationId) throws DataNotFoundException{

        Reservation reservation = this.findByReservation(reservationId);

        return reservationsMapper.mapFromReservation(reservationsRepository.saveAndFlush(reservation));

    }

    public ReservationDTO find(UUID reservationId) throws DataNotFoundException {
        return reservationsMapper.mapFromReservation(this.findByReservation(reservationId));
    }

    public Collection<ReservationDTO> list(){
        return reservationsMapper.mapFromReservationCollection(reservationsRepository.findAll());
    }

    public boolean delete(UUID reservationId) throws DataNotFoundException {
        Reservation reservation = this.findByReservation(reservationId);

        reservationsRepository.delete(reservation);

        return true;
    }


}
