package visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation;

import lombok.*;
import visitorreservation.visitorreservationapi.controller.DTO.commons.BaseDTO;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VisitorReservationDTO {

    private UUID id;

    @NotEmpty( message = "O nome do visitante deve ser iformado")
    private String visitorName;

    @NotEmpty( message = "O email do visitante deve ser iformado")
    private String visitorEmail;

    @NotEmpty(message = "O telefone do visitante deve ser informado")
    private String visitorPhone;

    @NotNull(message = "A data e hora da reserva deve ser informada")
    private LocalDateTime reservationDate;

}

