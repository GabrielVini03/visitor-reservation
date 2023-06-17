package visitorreservation.visitorreservationapi.model.entities;

import lombok.*;
import visitorreservation.visitorreservationapi.model.entities.commons.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Reservation extends BaseEntity {

    @Column(name = "reservation_date")
    private LocalDateTime reservationDate;

    @ManyToOne
    @JoinColumn(name = "visitor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visitor_reservation_fk"))
    private Visitor visitor;
}
