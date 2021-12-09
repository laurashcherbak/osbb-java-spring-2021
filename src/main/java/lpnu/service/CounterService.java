package lpnu.service;

import lpnu.dto.CounterDTO;
import java.util.List;

public interface CounterService {
    List<CounterDTO> getAllCounters();
    CounterDTO getCounterById(final Long id);
    CounterDTO saveCounter(final CounterDTO counter);
    CounterDTO updateCounter(final CounterDTO counterDTO);
    void deleteCounterById(final Long id);
}
