package visitorreservation.visitorreservationapi.model.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.CreateRequestVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.UpdateVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;
import visitorreservation.visitorreservationapi.model.mappers.ReservationsMapper;
import visitorreservation.visitorreservationapi.model.mappers.VisitorsMapper;
import visitorreservation.visitorreservationapi.model.repositories.ReservationsRepository;
import visitorreservation.visitorreservationapi.model.repositories.VisitorsRepository;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class VisitorReservationsService {

    private final VisitorsRepository visitorsRepository;
    private final ReservationsRepository reservationsRepository;

    private final VisitorsService visitorsService;

    private final ReservationsService reservationsService;

    private final VisitorsMapper visitorsMapper;

    private final ReservationsMapper reservationsMapper;

    @Autowired
    public VisitorReservationsService(VisitorsRepository visitorsRepository,
                                     ReservationsRepository reservationsRepository, VisitorsMapper visitorsMapper, ReservationsMapper reservationsMapper, ReservationsService reservationsService, VisitorsService visitorsService) {
        this.visitorsRepository = visitorsRepository;
        this.reservationsRepository = reservationsRepository;
        this.visitorsMapper = visitorsMapper;
        this.reservationsMapper = reservationsMapper;
        this.visitorsService = visitorsService;
        this.reservationsService = reservationsService;
    }

    public VisitorReservationDTO insert(CreateRequestVisitorReservationDTO createVisitorReservationDTO) throws ConstraintViolationException, DataNotFoundException, ValidationException {
        VisitorDTO visitorDTO = visitorsService.insert(
                CreateVisitorRequestDTO.builder()
                        .name(createVisitorReservationDTO.getVisitorName())
                        .email(createVisitorReservationDTO.getVisitorEmail())
                        .phone(createVisitorReservationDTO.getVisitorPhone())
                        .build()
        );

        ReservationDTO reservationDTO = reservationsService.insert(
                CreateReservationRequestDTO.builder()
                        .visitorId(visitorDTO.getId())
                        .reservationDate(createVisitorReservationDTO.getReservationDate())
                        .build()
        );

        return VisitorReservationDTO.builder()
                .id(reservationDTO.getId())
                .visitorName(visitorDTO.getName())
                .visitorEmail(visitorDTO.getEmail())
                .visitorPhone(visitorDTO.getPhone())
                .reservationDate(reservationDTO.getReservationDate())
                .build();
    }

    public VisitorReservationDTO update(UpdateVisitorReservationDTO updateVisitorReservationDTO, UUID visitorReservationId) throws DataNotFoundException  {


        return null;

    }

    public VisitorReservationDTO find(UUID reservationId) throws DataNotFoundException {
        ReservationDTO reservationDTO = reservationsService.find(reservationId);
        VisitorDTO visitorDTO = visitorsService.find(reservationDTO.getVisitor().getId());

        return VisitorReservationDTO.builder()
                .id(reservationId)
                .visitorName(visitorDTO.getName())
                .visitorEmail(visitorDTO.getEmail())
                .visitorPhone(visitorDTO.getPhone())
                .reservationDate(reservationDTO.getReservationDate())
                .build();
    }


    public Collection<VisitorReservationDTO> list() {
        Collection<ReservationDTO> reservationDTOCollection = reservationsService.list();

        Collection<VisitorReservationDTO> visitorReservationDTOCollection = new ArrayList<>();

        for (ReservationDTO reservationDTO : reservationDTOCollection) {
            VisitorDTO visitorDTO = visitorsService.find(reservationDTO.getVisitor().getId());

            VisitorReservationDTO visitorReservationDTO = VisitorReservationDTO.builder()
                    .id(reservationDTO.getId())
                    .visitorName(visitorDTO.getName())
                    .visitorEmail(visitorDTO.getEmail())
                    .visitorPhone(visitorDTO.getPhone())
                    .reservationDate(reservationDTO.getReservationDate())
                    .build();

            visitorReservationDTOCollection.add(visitorReservationDTO);
        }

        return visitorReservationDTOCollection;
    }


    public void delete(UUID reservationId) throws DataNotFoundException {

      ReservationDTO reservationDTO = reservationsService.find(reservationId);

        reservationsService.delete(reservationDTO.getId());
        visitorsService.delete(reservationDTO.getVisitor().getId());
    }

}
