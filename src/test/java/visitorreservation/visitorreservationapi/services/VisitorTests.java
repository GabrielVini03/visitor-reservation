package visitorreservation.visitorreservationapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.UpdateVisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;
import visitorreservation.visitorreservationapi.model.services.VisitorsService;
import visitorreservation.visitorreservationapi.services.commons.DataServiceTests;

import java.util.Objects;
import java.util.UUID;

public class VisitorTests extends DataServiceTests {

    VisitorsService visitorsService;

    @Autowired
    public void VisitorTests(VisitorsService visitorsService) {
        this.visitorsService = visitorsService;
    }

    @Test
    @DisplayName("Can insert visitor in the data base")
    public void insert() {

        CreateVisitorRequestDTO visitor = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        VisitorDTO visitorInserted = visitorsService.insert(visitor);

        Logger logger = LoggerFactory.getLogger(VisitorTests.class);
        logger.info("-------------------------- ID do visitante inserido: {} -----------------------", visitorInserted.getId());




        Assertions.assertEquals(visitorInserted.getEmail(), visitor.getEmail());
        Assertions.assertEquals(visitorInserted.getPhone(), visitor.getPhone());
        Assertions.assertEquals(visitorInserted.getName(), visitor.getName());
        Assertions.assertTrue(Objects.nonNull(visitorInserted.getId()));
    }

    @Test
    @DisplayName("Can´t insert visitor witch email that already in the data base")
    public void insertWitchSEmailExisting() {

       CreateVisitorRequestDTO visitor = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        visitorsService.insert(visitor);

       Assertions.assertThrows(DataNotFoundException.class, () -> visitorsService.insert(visitor));
    }

    @Test
    @DisplayName("Can only update the visitor email in the data base")
    public void updateOnlyEmail(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        UUID visitorInserted = visitorsService.insert(createVisitorRequestDTO).getId();

        UpdateVisitorDTO updateVisitorDTO = UpdateVisitorDTO.builder()
                .email("clarencio@gmail.com")
                .build();

         VisitorDTO visitorWitchUpdates = visitorsService.update(updateVisitorDTO, visitorInserted);

        Assertions.assertNotEquals(visitorWitchUpdates.getEmail(),createVisitorRequestDTO.getEmail());
        Assertions.assertEquals(visitorWitchUpdates.getName(), createVisitorRequestDTO.getName());
        Assertions.assertEquals(visitorWitchUpdates.getPhone(),createVisitorRequestDTO.getPhone());
        Assertions.assertEquals(visitorWitchUpdates.getId(), visitorInserted);
        Assertions.assertEquals(visitorWitchUpdates.getEmail(), updateVisitorDTO.getEmail());

    }

    @Test
    @DisplayName("Can only update the visitor name in the data base")
    public void updateOnlyName(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        UUID visitorInserted = visitorsService.insert(createVisitorRequestDTO).getId();

        UpdateVisitorDTO updateVisitorDTO = UpdateVisitorDTO.builder()
                .name("Joao")
                .build();

        VisitorDTO visitorWitchUpdates = visitorsService.update(updateVisitorDTO, visitorInserted);

        Assertions.assertEquals(visitorWitchUpdates.getEmail(),createVisitorRequestDTO.getEmail());
        Assertions.assertNotEquals(visitorWitchUpdates.getName(), createVisitorRequestDTO.getName());
        Assertions.assertEquals(visitorWitchUpdates.getPhone(),createVisitorRequestDTO.getPhone());
        Assertions.assertEquals(visitorWitchUpdates.getId(), visitorInserted);
        Assertions.assertEquals(visitorWitchUpdates.getName(), updateVisitorDTO.getName());

    }

    @Test
    @DisplayName("Can only update the visitor name in the data base")
    public void updateOnlyPhone(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        UUID visitorInserted = visitorsService.insert(createVisitorRequestDTO).getId();

        UpdateVisitorDTO updateVisitorDTO = UpdateVisitorDTO.builder()
                .name("Joao")
                .build();

        VisitorDTO visitorWitchUpdates = visitorsService.update(updateVisitorDTO, visitorInserted);

        Assertions.assertEquals(visitorWitchUpdates.getEmail(),createVisitorRequestDTO.getEmail());
        Assertions.assertNotEquals(visitorWitchUpdates.getName(), createVisitorRequestDTO.getName());
        Assertions.assertEquals(visitorWitchUpdates.getPhone(),createVisitorRequestDTO.getPhone());
        Assertions.assertEquals(visitorWitchUpdates.getId(), visitorInserted);
        Assertions.assertEquals(visitorWitchUpdates.getName(), updateVisitorDTO.getName());

    }
    @Test
    @DisplayName("Can´t update visitor with email existing in the data base")
    public void updateSameEmail(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        visitorsService.insert(createVisitorRequestDTO);

        CreateVisitorRequestDTO createVisitorRequestDTO2 = CreateVisitorRequestDTO.builder()
                .name("Clarencio")
                .email("clare@gmail.com")
                .phone("(47) 99399-4506")
                .build();

        UUID visitorID = visitorsService.insert(createVisitorRequestDTO2).getId();

        UpdateVisitorDTO updateVisitorDTO = UpdateVisitorDTO.builder()
                .email(createVisitorRequestDTO.getEmail())
                .build();

        Assertions.assertThrows(DataNotFoundException.class, () -> visitorsService.update(updateVisitorDTO, visitorID));

    }

    @Test
    @DisplayName("Can´t update visitor with phone existing in the data base")
    public void updateSamePhone(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        visitorsService.insert(createVisitorRequestDTO);

        CreateVisitorRequestDTO createVisitorRequestDTO2 = CreateVisitorRequestDTO.builder()
                .name("Clarencio")
                .email("clare@gmail.com")
                .phone("(47) 99399-4506")
                .build();

        UUID visitorID = visitorsService.insert(createVisitorRequestDTO2).getId();

        UpdateVisitorDTO updateVisitorDTO = UpdateVisitorDTO.builder()
                .phone(createVisitorRequestDTO.getPhone())
                .build();

        Assertions.assertThrows(DataNotFoundException.class, () -> visitorsService.update(updateVisitorDTO, visitorID));

    }

    @Test
    @DisplayName("Can only update the visitor email in the data base")
    public void updateAllParameters(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        UUID visitorInserted = visitorsService.insert(createVisitorRequestDTO).getId();

        UpdateVisitorDTO updateVisitorDTO = UpdateVisitorDTO.builder()
                .name("Flavio")
                .email("clarencio@gmail.com")
                .phone("(61) 98323-4532")
                .build();

        VisitorDTO visitorWitchUpdates = visitorsService.update(updateVisitorDTO, visitorInserted);

        Assertions.assertNotEquals(visitorWitchUpdates.getEmail(),createVisitorRequestDTO.getEmail());
        Assertions.assertNotEquals(visitorWitchUpdates.getName(), createVisitorRequestDTO.getName());
        Assertions.assertNotEquals(visitorWitchUpdates.getPhone(),createVisitorRequestDTO.getPhone());
        Assertions.assertEquals(visitorWitchUpdates.getId(), visitorInserted);
        Assertions.assertEquals(visitorWitchUpdates.getEmail(), updateVisitorDTO.getEmail());
        Assertions.assertEquals(visitorWitchUpdates.getName(), updateVisitorDTO.getName());
        Assertions.assertEquals(visitorWitchUpdates.getPhone(), updateVisitorDTO.getPhone());

    }


    @Test
    @DisplayName("Can find visitor in the data base")
    public void find(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

      UUID visitorId =  visitorsService.insert(createVisitorRequestDTO).getId();

      Assertions.assertTrue(Objects.nonNull(visitorsService.find(visitorId)));
    }

    @Test
    @DisplayName("Can find visitor in the data base")
    public void list(){

        CreateVisitorRequestDTO createVisitorRequestDTO = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        UUID visitorId =  visitorsService.insert(createVisitorRequestDTO).getId();

        Assertions.assertFalse(visitorsService.list().isEmpty());
    }

    @Test
    @DisplayName("Can delete a player from the database")
    public void delete()  {

        CreateVisitorRequestDTO visitor = CreateVisitorRequestDTO.builder()
                .name("Roberto")
                .email("boberto@gmail.com")
                .phone("(47) 99739-4506")
                .build();

        VisitorDTO visitorInserted = visitorsService.insert(visitor);


        Assertions.assertTrue(visitorsService.delete(visitorInserted.getId()));

        Assertions.assertThrows(DataNotFoundException.class, () -> visitorsService.find(visitorInserted.getId()));
    }

}
