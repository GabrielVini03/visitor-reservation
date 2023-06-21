package visitorreservation.visitorreservationapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.CreateReservationRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.UpdateReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.model.services.ReservationsService;
import visitorreservation.visitorreservationapi.model.services.VisitorsService;
import visitorreservation.visitorreservationapi.services.commons.DataServiceTests;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class ReservationTests extends DataServiceTests {

    ReservationsService reservationsService;

    VisitorsService visitorsService;

    @Autowired
    public void ReservationTests(ReservationsService reservationsService, VisitorsService visitorsService){
        this.reservationsService = reservationsService;
        this.visitorsService = visitorsService;
    }

    @Test
    @DisplayName("Can insert reservation in the data base")
    public void insert() {

        UUID visitor = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.now();

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitor)
                .reservationDate(localDateTime)
                .build();

        ReservationDTO reservationDTO = reservationsService.insert(createReservationRequestDTO);

        Assertions.assertEquals(createReservationRequestDTO.getVisitorId(), reservationDTO.getVisitor().getId());
        Assertions.assertEquals(createReservationRequestDTO.getReservationDate(), reservationDTO.getReservationDate() );
        Assertions.assertTrue(Objects.nonNull(reservationDTO.getId()));
    }

    @Test
    @DisplayName("Can´t insert reservation with visitor and local data time that already in the data base")
    public void insertWithSVisitorAndLocalDataTimeExisting() {
        UUID visitor = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.now();

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitor)
                .reservationDate(localDateTime)
                .build();

        reservationsService.insert(createReservationRequestDTO);

        Assertions.assertThrows(DataNotFoundException.class, () -> reservationsService.insert(createReservationRequestDTO));

      }

    @Test
    @DisplayName("Can update only visitor in the data base")
    public void updateOnlyVisitor(){

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        UUID visitorId2 = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Clarencio")
                .email("clare@gmail.com")
                .phone("(47) 99399-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.now();

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(localDateTime)
                .build();

       UUID reservationId = reservationsService.insert(createReservationRequestDTO).getId();

        UpdateReservationDTO updateVisitorDTO = UpdateReservationDTO.builder()
                .visitorId(visitorId2)
                .build();

        ReservationDTO reservationWitchUpdates = reservationsService.update(updateVisitorDTO, reservationId);

        Assertions.assertEquals(reservationWitchUpdates.getReservationDate(), createReservationRequestDTO.getReservationDate());
        Assertions.assertNotEquals(reservationWitchUpdates.getVisitor().getId(), createReservationRequestDTO.getVisitorId());
        Assertions.assertEquals(reservationWitchUpdates.getId(), reservationId);
        Assertions.assertEquals(reservationWitchUpdates.getVisitor().getId(), updateVisitorDTO.getVisitorId());
    }

    @Test
    @DisplayName("Can update only local data time on reservation in the data base")
    public void updateOnlyLocalDataTime(){

        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.NOON).minusHours(2);

        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.NOON);

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(localDateTime)
                .build();

        UUID reservationId = reservationsService.insert(createReservationRequestDTO).getId();

        UpdateReservationDTO updateVisitorDTO = UpdateReservationDTO.builder()
                .reservationDate(localDateTime2)
                .build();

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        ReservationDTO reservationWitchUpdates = reservationsService.update(updateVisitorDTO, reservationId);

        Assertions.assertNotEquals(reservationWitchUpdates.getReservationDate(), createReservationRequestDTO.getReservationDate());
        Assertions.assertEquals(reservationWitchUpdates.getVisitor().getId(), createReservationRequestDTO.getVisitorId());
        Assertions.assertEquals(reservationWitchUpdates.getId(), reservationId);
        Assertions.assertEquals(reservationWitchUpdates.getReservationDate().format(formatter), updateVisitorDTO.getReservationDate().format(formatter));
    }

    @Test
    @DisplayName("Can´t update reservation with local data time that already in the data base")
    public void updateWithSameLocalDataTime() {
        UUID visitorId = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        UUID visitorId2 = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Clarencio")
                .email("clare@gmail.com")
                .phone("(47) 99399-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.NOON).minusHours(2);

        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.NOON);

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitorId)
                .reservationDate(localDateTime)
                .build();

        UUID reservationId = reservationsService.insert(createReservationRequestDTO).getId();

        ReservationDTO createReservationRequestDTO2 = reservationsService.insert(CreateReservationRequestDTO.builder()
                .visitorId(visitorId2)
                .reservationDate(localDateTime2)
                .build());

        UpdateReservationDTO updateVisitorDTO = UpdateReservationDTO.builder()
                .visitorId(visitorId)
                .reservationDate(localDateTime2)
                .build();

        Assertions.assertThrows(DataNotFoundException.class, () -> reservationsService.update(updateVisitorDTO, reservationId));
    }

    @Test
    @DisplayName("Can find reservation in the data base")
    public void find(){

        UUID visitor = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.now();

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitor)
                .reservationDate(localDateTime)
                .build();

        UUID reservationId = reservationsService.insert(createReservationRequestDTO).getId();

        Assertions.assertTrue(Objects.nonNull(reservationsService.find(reservationId)));
    }

    @Test
    @DisplayName("Can find reservation in the data base")
    public void list(){

        UUID visitor = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.now();

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitor)
                .reservationDate(localDateTime)
                .build();

        reservationsService.insert(createReservationRequestDTO);

        Assertions.assertFalse(reservationsService.list().isEmpty());
    }

    @Test
    @DisplayName("Can delete a reservation from the database")
    public void delete()  {

        UUID visitor = visitorsService.insert(CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build()).getId();

        LocalDateTime localDateTime = LocalDateTime.now();

        CreateReservationRequestDTO createReservationRequestDTO = CreateReservationRequestDTO.builder()
                .visitorId(visitor)
                .reservationDate(localDateTime)
                .build();

        UUID reservationId = reservationsService.insert(createReservationRequestDTO).getId();


        Assertions.assertTrue(reservationsService.delete(reservationId));

        Assertions.assertThrows(DataNotFoundException.class, () -> reservationsService.find(reservationId));
    }

}
