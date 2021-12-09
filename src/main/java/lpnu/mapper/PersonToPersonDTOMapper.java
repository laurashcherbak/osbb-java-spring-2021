package lpnu.mapper;

import lpnu.dto.PersonDTO;
import lpnu.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDTOMapper {
    public Person toEntity(final PersonDTO personDTO) {
        final Person person = new Person();
        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setCompanyId(personDTO.getCompanyId());
        return person;
    }

    public PersonDTO toDTO(final Person person) {
        final PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setCompanyId(person.getCompanyId());
        return personDTO;
    }
}
