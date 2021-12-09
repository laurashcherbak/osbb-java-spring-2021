package lpnu.service.impl;

import lpnu.dto.PersonDTO;
import lpnu.entity.Person;
import lpnu.exception.ServiceException;
import lpnu.mapper.PersonToPersonDTOMapper;
import lpnu.repository.PersonRepository;
import lpnu.service.CompanyService;
import lpnu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonToPersonDTOMapper personMapper;

    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private CompanyService companyService;

//    public PersonServiceImpl(final PersonToPersonDTOMapper personMapper, final PersonRepository personRepository) {
//        this.personMapper = personMapper;
//        this.personRepository = personRepository;
//    }

    @Override
    public List<PersonDTO> getAllPersons() {
        //перетворення між User і UserDTO => треба створити мапи
        return personRepository.getAllPersons().stream()
                //.map(personMapper::toDTO)
                .map(e -> personMapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(final Long id) {
        return personMapper.toDTO(personRepository.getPersonById(id));
    }

    @Override
    public PersonDTO savePerson(final PersonDTO personDTO) {
        final Person person = personMapper.toEntity(personDTO);
        if (companyService.getCompanyById(person.getCompanyId()) == null) {
            throw new ServiceException(400, "Company with id {" + person.getCompanyId() + "} not found");
        }
        //counter.setCounterState(CounterState.NOT_APPROVED);
        return personMapper.toDTO(personRepository.savePerson(person));
    }

    @Override
    public PersonDTO updatePerson(final PersonDTO personDTO) {
        final Person person = personMapper.toEntity(personDTO);
        if (companyService.getCompanyById(person.getCompanyId()) == null) {
            throw new ServiceException(400, "Company with id {" + person.getCompanyId() + "} not found");
        }
        return personMapper.toDTO(personRepository.updatePerson(person));
    }

    @Override
    public void deletePersonById(final Long id) {
        personRepository.deletePerson(id);
    }
}
