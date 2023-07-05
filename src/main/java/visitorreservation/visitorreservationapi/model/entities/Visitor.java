package visitorreservation.visitorreservationapi.model.entities;

import lombok.*;
import visitorreservation.visitorreservationapi.model.entities.commons.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "visitor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Visitor extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
