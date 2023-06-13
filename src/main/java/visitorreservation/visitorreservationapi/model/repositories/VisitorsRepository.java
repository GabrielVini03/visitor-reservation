package visitorreservation.visitorreservationapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import visitorreservation.visitorreservationapi.model.entities.Visitor;

import java.util.UUID;

public interface VisitorsRepository extends JpaRepository<Visitor, UUID> {
}
