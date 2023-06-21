package visitorreservation.visitorreservationapi.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import visitorreservation.visitorreservationapi.model.entities.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, UUID> {

    @Query(value = "select r from Reservation r where reservationDate < :upperBound and reservationDate >= :lowerBound" )
    List<Reservation> findByReservationDate(
            @Param("upperBound") LocalDateTime upperBound,
            @Param("lowerBound") LocalDateTime lowerBound);
}
