package visitorreservation.visitorreservationapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import visitorreservation.visitorreservationapi.model.entities.Visitor;

import java.util.UUID;

@Repository
public interface VisitorsRepository extends JpaRepository<Visitor, UUID> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
