package visitorreservation.visitorreservationapi.model.entities.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"createdBy","updatedBy","createdDate", "updatedDate"})
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name="created_by")
    private UUID createdBy;

    @Column(name="updated_by")
    private UUID updatedBy;

    @Column(name="created_by_ip_address")
    private String createdByIPAddress;

    @Column(name="updated_by_ip_address")
    private String updatedByIPAddress;

    @Column(name="created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name="updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;
}
