package visitorreservation.visitorreservationapi.controller.DTO.domains.visitorReservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateVisitorReservationDTO {

    private String visitorName;

    private String visitorEmail;

    private String visitorPhone;

    private LocalDateTime reservationDate;

}


