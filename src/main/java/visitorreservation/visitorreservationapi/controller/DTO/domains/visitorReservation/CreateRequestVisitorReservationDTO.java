package visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateRequestVisitorReservationDTO {

        @NotEmpty( message = "O nome do visitante deve ser iformado")
        private String visitorName;

        @NotEmpty( message = "O email do visitante deve ser iformado")
        private String visitorEmail;

        @NotEmpty(message = "O telefone do visitante deve ser informado")
        private String visitorPhone;

        @NotNull(message = "A data e hora da reserva deve ser informada")
        private LocalDateTime reservationDate;

}
