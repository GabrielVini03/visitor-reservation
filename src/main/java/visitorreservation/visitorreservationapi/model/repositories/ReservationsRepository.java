package visitorreservation.visitorreservationapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import visitorreservation.visitorreservationapi.model.entities.Reservation;

import java.util.UUID;

public interface ReservationsRepository extends JpaRepository<Reservation, UUID> {
}
