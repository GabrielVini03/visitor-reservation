package visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateVisitorReservationDTO {

    private UUID reservationId;

    private UUID visitorID;

}


