package visitorreservation.visitorreservationapi.model.entities;

import lombok.*;
import visitorreservation.visitorreservationapi.model.entities.commons.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "visitor_reservation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VisitorReservation extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "visitor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visitor_reservation_visitor_fk"))
    private Visitor visitor;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "visitor_reservation_reservation_fk"))
    private Reservation reservation;

}

