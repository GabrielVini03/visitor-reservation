package visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation;

import lombok.*;
import visitorreservation.visitorreservationapi.controller.DTO.commons.BaseDTO;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VisitorReservationDTO extends BaseDTO<UUID> {

    @NotNull(message = "O id do visitante deve ser informado")
    private UUID visitorId;

    @NotNull(message = "O id da reserva deve ser informado")
    private UUID reservationId;
}

