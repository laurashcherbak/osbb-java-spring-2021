package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Counter;
import lpnu.util.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CounterRepository {
    private List<Counter> counters;

    private Long id = 1L;

    @PostConstruct
    public void init() {
        final Path file = Paths.get("counters.txt");
        try {
            final String savedCountersAsString = Files.readString(file, StandardCharsets.UTF_16);
            counters = JacksonUtil.deserialize(savedCountersAsString, new TypeReference<List<Counter>>() {});

            if (counters == null) {
                counters = new ArrayList<>();
                return;
            }

            final Long maxId = counters.stream().mapToLong(Counter::getId).max().orElse(1);

            this.id = maxId + 1;


        } catch (final Exception e){
            System.out.println("We have an issue");
            counters = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("counters.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(counters), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }


    public List<Counter> getAllCounters() {
        //return counters.stream().filter(e -> e.getCounterState() == CounterState.ACTIVE).collect(Collectors.toList());
        return new ArrayList<>(counters);
    }

    public Counter getCounterById(final Long id) {
        return counters.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElse(null);
                //.orElseThrow(() -> new ServiceException(400, "counter with id {" + id + "} not found"));
    }

    public Counter saveCounter(final Counter counter) {
//        final Counter existingCounter = getCounterById(counter.getId());
//        if (existingCounter != null) {
//            existingCounter.setName(counter.getName());
//            existingCounter.setMeterReading(counter.getMeterReading());
//            existingCounter.setPersonId(counter.getPersonId());
//            return existingCounter;
//        }

//        counter.setId(id);
//        ++id;
        counters.add(counter);
        return counter;
    }

    public Counter updateCounter(final Counter counter) {
        final Counter savedCounter = getCounterById(counter.getId());
        savedCounter.setName(counter.getName());
        savedCounter.setMeterReading(counter.getMeterReading());
        savedCounter.setPersonId(counter.getPersonId());
        return savedCounter;
    }

    public void deleteCounterById(final Long id) {
        counters = counters.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }

}
