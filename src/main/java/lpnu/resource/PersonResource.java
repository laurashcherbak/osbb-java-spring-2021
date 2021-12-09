package lpnu.resource;

import lpnu.dto.PersonDTO;
import lpnu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Rest повертає об'єкт перетворений в JSON
// Controller зазвичай повертає одну стрінгу яка є назвою сторінки яку треба повернути
//@RequestMapping("/api/v1")
public class PersonResource {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons") //returns all persons
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public PersonDTO getPersonById(@PathVariable final Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/persons")
    public PersonDTO savePerson(@RequestBody @Validated final PersonDTO personDTO) {
        return personService.savePerson(personDTO);
    }

    @PutMapping("/persons")
    public PersonDTO updatePerson(@RequestBody @Validated final PersonDTO personDTO) {
        return personService.updatePerson(personDTO);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity deletePersonById(@PathVariable final Long id) {
        personService.deletePersonById(id);
        return ResponseEntity.ok().build();
    }

}
