package lpnu.mapper;

import lpnu.dto.CounterDTO;
import lpnu.entity.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterToCounterDTOMapper {
    public Counter toEntity(final CounterDTO counterDTO) {
        final Counter counter = new Counter();
        counter.setId(counterDTO.getId());
        counter.setName(counterDTO.getName());
        counter.setMeterReading(counterDTO.getMeterReading());
        counter.setPersonId(counterDTO.getPersonId());
        return counter;
    }

    public CounterDTO toDTO(final Counter counter) {
        final CounterDTO counterDTO = new CounterDTO();
        counterDTO.setId(counter.getId());
        counterDTO.setName(counter.getName());
        counterDTO.setMeterReading(counter.getMeterReading());
        counterDTO.setPersonId(counter.getPersonId());
        return counterDTO;
    }

}
