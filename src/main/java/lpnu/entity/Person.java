package lpnu.entity;

import lombok.*;

@Data // get, set, equals, hashcode, toString
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Person {
    private Long id;
    private String name;
    private Long companyId;
}
