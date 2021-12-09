package lpnu.entity;

import lombok.*;

@Data // get, set, equals, hashcode, toString
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Counter {
    private Long id;
    private String name;//number
    private int meterReading;
    private Long personId;
}
