package lpnu.service;

import lpnu.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();
    PersonDTO getPersonById(final Long id);
    PersonDTO savePerson(final PersonDTO personDTO);
    PersonDTO updatePerson(final PersonDTO personDTO);
    void deletePersonById(final Long id);
}
