package visitorreservation.visitorreservationapi.services.commons;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest
@ComponentScan(value = {"visitorreservation.visitorreservationapi.model"})
public class DataServiceTests {

    @BeforeEach
    void clearDataBase(@Autowired Flyway flyway){
        flyway.clean();
        flyway.migrate();
    }
}
