package visitorreservation.visitorreservationapi.controller.DTO.domains;

import lombok.*;
import visitorreservation.visitorreservationapi.controller.DTO.commons.BaseDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateVisitorDTO extends BaseDTO {

    @Size(max = 128, message = "O nome do visitante deve ter no máximo 128 caracteres")
    private String name;

    @Size(max = 128, message = "O e-mail do visitante deve ter no máximo 128 caracteres")
    @Email(message = "O e-mail do visitante deve estar em um formato válido")
    private String email;

    @Size(max = 20, message = "O telefone do visitante deve ter no máximo 20 caracteres")
    private String phone;

}
