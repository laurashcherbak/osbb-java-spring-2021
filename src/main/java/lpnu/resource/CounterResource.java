package lpnu.resource;

import lpnu.dto.CounterDTO;
import lpnu.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CounterResource {

    @Autowired
    private CounterService counterService;

    @GetMapping("/counters")
    public List<CounterDTO> getAllCounters() {

        return counterService.getAllCounters();
    }

    @GetMapping("/counters/{id}")
    public CounterDTO getCounterById(@PathVariable final Long id) {

        return counterService.getCounterById(id);
    }

    @PostMapping("/counters")
    public CounterDTO saveCounter(@RequestBody @Validated final CounterDTO counter) {
        return counterService.saveCounter(counter);
    }

    @PutMapping("/counters")
    public CounterDTO updateCounter(@RequestBody @Validated final CounterDTO counter) {
//        return counterService.saveCounter(counter);
        return counterService.updateCounter(counter);
    }

    @DeleteMapping("/counters/{id}")
    public ResponseEntity deleteCounterById(@PathVariable final Long id) {
        counterService.deleteCounterById(id);
        return ResponseEntity.ok().build();
    }


}
