package lpnu.service.impl;

import lpnu.dto.CounterDTO;
import lpnu.entity.Counter;
import lpnu.exception.ServiceException;
import lpnu.mapper.CounterToCounterDTOMapper;
import lpnu.repository.CounterRepository;
import lpnu.service.CounterService;
import lpnu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private CounterToCounterDTOMapper counterMapper;

    @Autowired
    private PersonService personService;

    @Override
    public List<CounterDTO> getAllCounters() {
        return counterRepository.getAllCounters().stream()
                .map(counterMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CounterDTO getCounterById(final Long id) {
        return counterMapper.toDTO(counterRepository.getCounterById(id));
    }

    @Override
    public CounterDTO saveCounter(final CounterDTO counterDTO) {
        final Counter counter = counterMapper.toEntity(counterDTO);
        if (personService.getPersonById(counter.getPersonId()) == null) {
            throw new ServiceException(400, "Person with id {" + counter.getPersonId() + "} not found");
        }
        //counter.setCounterState(CounterState.NOT_APPROVED);
        return counterMapper.toDTO(counterRepository.saveCounter(counter));
    }

    @Override
    public CounterDTO updateCounter(final CounterDTO counterDTO) {
        final Counter counter = counterMapper.toEntity(counterDTO);
        if (personService.getPersonById(counter.getPersonId()) == null) {
            throw new ServiceException(400, "Person with id {" + counter.getPersonId() + "} not found");
        }
        //counter.setCounterState(CounterState.NOT_APPROVED);
        return counterMapper.toDTO(counterRepository.updateCounter(counter));
    }

    @Override
    public void deleteCounterById(final Long id) {
        //final Counter savedCounter = counterRepository.getCounterById(id);
        //savedCounter.setCounterState(CounterState.DELETED);
        counterRepository.deleteCounterById(id);
    }
}
