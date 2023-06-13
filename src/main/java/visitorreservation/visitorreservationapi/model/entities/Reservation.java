package visitorreservation.visitorreservationapi.model.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import visitorreservation.visitorreservationapi.model.entities.commons.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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
