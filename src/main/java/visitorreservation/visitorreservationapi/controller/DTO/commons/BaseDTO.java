package visitorreservation.visitorreservationapi.controller.DTO.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO<ID> {
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ID id;
}