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
//@CrossCheck(field1 = "meterReading", condition = Condition.GREATER_THAN, message = "We need to make a profit!!!")
public class CounterDTO {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private int meterReading;

    @NotNull
    private Long personId;

}
