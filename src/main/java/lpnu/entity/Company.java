package lpnu.dto;

import lombok.*;

@Data // get, set, equals, hashcode, toString
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Company {
    private Long id;
    private String name;
}
