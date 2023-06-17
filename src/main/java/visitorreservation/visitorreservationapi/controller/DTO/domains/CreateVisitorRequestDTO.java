package visitorreservation.visitorreservationapi.controller.DTO.domains;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateVisitorRequestDTO {

    @NotEmpty(message = "O nome do visitante deve ser informado")
    @Size(max = 128, message = "O nome do visitante deve ter no máximo 128 caracteres")
    private String name;

    @NotEmpty(message = "O e-mail do visitante deve ser informado")
    @Size(max = 128, message = "O e-mail do visitante deve ter no máximo 128 caracteres")
    @Email(message = "O e-mail do visitante deve estar em um formato válido")
    private String email;

    @NotEmpty(message = "O telefone do visitante deve ser informado")
    @Size(max = 20, message = "O telefone do visitante deve ter no máximo 20 caracteres")
    private String phone;

}
