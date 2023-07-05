package visitorreservation.visitorreservationapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import visitorreservation.visitorreservationapi.model.entities.VisitorReservation;

import java.util.UUID;

@Repository
public interface VisitorReservationsRepository extends JpaRepository<VisitorReservation, UUID> {
}
