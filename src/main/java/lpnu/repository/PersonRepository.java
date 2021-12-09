package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Person;
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
public class PersonRepository {
    private List<Person> persons;

    private Long id = 1L;

    @PostConstruct
    public void init() {

        final Path file = Paths.get("persons.txt");
        try {
            final String savedUsersAsString = Files.readString(file, StandardCharsets.UTF_16);
            persons = JacksonUtil.deserialize(savedUsersAsString, new TypeReference<List<Person>>() {});

            if (persons == null) {
                persons = new ArrayList<>();
                return;
            }

            final Long maxId = persons.stream().mapToLong(Person::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e){
            System.out.println("We have an issue");
            persons = new ArrayList<>();
        }

    }

    @PreDestroy
    public void preDestroy(){
        final Path file = Paths.get("persons.txt");
        try {
            Files.writeString(file, JacksonUtil.serialize(persons), StandardCharsets.UTF_16);
        } catch (final Exception e){
            System.out.println("We have an issue");
        }
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(persons); // щоб ліст persons не можна було змінити з service
    }


    public Person getPersonById(final Long id) {
        return persons.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person savePerson(final Person person) {
        persons.add(person);
        return person;
    }

    public Person updatePerson(final Person person) {
        final Person savedPerson = getPersonById(person.getId());
        savedPerson.setName(person.getName());
        savedPerson.setCompanyId(person.getCompanyId());
        return savedPerson;
    }

    public void deletePerson(final Long id) {
        persons = persons.stream()
                .filter(e -> e.getId() != id)
                .collect(Collectors.toList());
    }

}
