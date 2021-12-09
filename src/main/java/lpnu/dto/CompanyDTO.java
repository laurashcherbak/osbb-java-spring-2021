package lpnu.dto;

import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data // get, set, equals, hashcode, toString
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CompanyDTO {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

}
