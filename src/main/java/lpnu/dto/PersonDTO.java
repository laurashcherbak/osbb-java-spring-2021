package lpnu.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data // get, set, equals, hashcode, toString
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonDTO {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long companyId;

}
