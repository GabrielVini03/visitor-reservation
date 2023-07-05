package visitorreservation.visitorreservationapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.CreateRequestVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.UpdateVisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation.VisitorReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.model.services.ReservationsService;
import visitorreservation.visitorreservationapi.model.services.VisitorReservationsService;
import visitorreservation.visitorreservationapi.model.services.VisitorsService;
import visitorreservation.visitorreservationapi.services.commons.DataServiceTests;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class VisitorReservationsTests extends DataServiceTests {

    private VisitorReservationsService visitorReservationsService;

    private VisitorsService visitorsService;

    private ReservationsService reservationsService;

    @Autowired
    public void VisitorReservationsService(VisitorReservationsService visitorReservationsService, ReservationsService reservationsService, VisitorsService visitorsService) {
        this.visitorReservationsService = visitorReservationsService;
        this.reservationsService = reservationsService;
        this.visitorsService = visitorsService;
    }


    @Test
    @DisplayName("Can insert a visitor reservation")
    public void insertVisitorReservation() {

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationId)
                .build();

        VisitorReservationDTO createdVisitorReservation = visitorReservationsService.insert(visitorReservationDTO);
        Assertions.assertTrue(Objects.nonNull(createdVisitorReservation.getId()));

     }

    @Test
    @DisplayName("Can't insert a visitor reservation with not found visitor")
    public void insertVisitorReservationWithNotFoundVisitor() {

        UUID visitorIdNotFound = UUID.randomUUID();

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorIdNotFound)
                .reservationId(reservationId)
                .build();

        Assertions.assertThrows(DataNotFoundException.class, () -> {
            visitorReservationsService.insert(visitorReservationDTO);
        });

    }

    @Test
    @DisplayName("Can't insert a visitor reservation with not found reservation")
    public void insertVisitorReservationWithNotFoundReservation() {

        UUID reservationIdNotFound = UUID.randomUUID();

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationIdNotFound)
                .build();

        Assertions.assertThrows(DataNotFoundException.class, () -> {
            visitorReservationsService.insert(visitorReservationDTO);
        });
    }

    @Test
    @DisplayName("Can update only visitor in visitor reservation")
    public void updateVisitorReservationOnlyVisitor() throws DataNotFoundException {

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        UUID visitorId2 = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberta")
                .email("boberta@gmail.com")
                .phone("(47) 98739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();


        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationId)
                .build();

        VisitorReservationDTO createdVisitorReservation = visitorReservationsService.insert(visitorReservationDTO);

        UpdateVisitorReservationDTO updateVisitorReservationDTO = UpdateVisitorReservationDTO.builder()
                .visitorID(visitorId2)
                .build();

        VisitorReservationDTO visitorReservationWithUpdate = visitorReservationsService.update(updateVisitorReservationDTO, createdVisitorReservation.getId());

        Assertions.assertEquals(visitorReservationWithUpdate.getReservationId(), createdVisitorReservation.getReservationId());
        Assertions.assertNotEquals(visitorReservationWithUpdate.getVisitorId(), createdVisitorReservation.getVisitorId());
        Assertions.assertEquals(visitorReservationWithUpdate.getId(), createdVisitorReservation.getId());

    }

    @Test
    @DisplayName("Can update only reservation in visitor reservation")
    public void updateVisitorReservationOnlyReservation() throws DataNotFoundException {

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();
        LocalDateTime reservationDate2 = LocalDateTime.now().minusHours(3);

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        UUID reservationId2 = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate2)
                .build()).getId();


        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationId)
                .build();

        VisitorReservationDTO createdVisitorReservation = visitorReservationsService.insert(visitorReservationDTO);

        UpdateVisitorReservationDTO updateVisitorReservationDTO = UpdateVisitorReservationDTO.builder()
                .reservationId(reservationId2)
                .build();

        VisitorReservationDTO visitorReservationWithUpdate = visitorReservationsService.update(updateVisitorReservationDTO, createdVisitorReservation.getVisitorId());

        Assertions.assertEquals(visitorReservationWithUpdate.getReservationId(), createdVisitorReservation.getReservationId());
        Assertions.assertNotEquals(visitorReservationWithUpdate.getVisitorId(), createdVisitorReservation.getVisitorId());
        Assertions.assertEquals(visitorReservationWithUpdate.getId(), createdVisitorReservation.getId());

    }

    @Test
    @DisplayName("Can find visitor reservation in the data base")
    public void find(){

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationId)
                .build();

        UUID visitorReservationId = visitorReservationsService.insert(visitorReservationDTO).getId();

        Assertions.assertTrue(Objects.nonNull(visitorReservationsService.find(visitorReservationId)));
    }

    @Test
    @DisplayName("Can find visitor reservation in the data base")
    public void list(){

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationId)
                .build();

        visitorReservationsService.insert(visitorReservationDTO);

        Assertions.assertFalse(visitorReservationsService.list().isEmpty());
    }

    @Test
    @DisplayName("Can delete a visitor reservation from the database")
    public void delete()  {

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime reservationDate = LocalDateTime.now();

        UUID reservationId = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(reservationDate)
                .build()).getId();

        CreateRequestVisitorReservationDTO visitorReservationDTO = CreateRequestVisitorReservationDTO.builder()
                .visitorId(visitorId)
                .reservationId(reservationId)
                .build();

       UUID visitorReservationId = visitorReservationsService.insert(visitorReservationDTO).getId();
        Assertions.assertTrue(visitorReservationsService.delete(visitorReservationId));

        Assertions.assertThrows(DataNotFoundException.class, () -> visitorReservationsService.find(visitorReservationId));
    }

}
