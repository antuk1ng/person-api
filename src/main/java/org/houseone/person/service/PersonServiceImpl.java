package org.houseone.person.service;

import lombok.AllArgsConstructor;
import org.houseone.person.entity.PersonEntity;
import org.houseone.person.model.Person;
import org.houseone.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final Function<PersonEntity, Person> personEntityToPerson = entity ->
            new Person(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());

    private final Function<Person, PersonEntity> personToPersonEntity = person ->
            new PersonEntity(person.getId(), person.getFirstName(), person.getLastName(), person.getEmail());

    @Override
    public Person getPersonById(String id) {
        return personRepository.findById(id)
                .map(personEntityToPerson)
                .orElse(null);
    }

    @Override
    public List<Person> getAllPersons() {
        List<PersonEntity> personEntityList = personRepository.findAll();

        return personEntityList.stream()
                .map(personEntityToPerson)
                .collect(Collectors.toList());
    }

    @Override
    public Person addPerson(Person person) {
        person.setId(getGeneratedId());

        PersonEntity personToAdd = personToPersonEntity.apply(person);

        PersonEntity addedPerson = personRepository.save(personToAdd);

        return personEntityToPerson.apply(addedPerson);
    }

    @Override
    public Person updatePerson(String id, Person person) {
        Person existingPerson = getPersonById(id);
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setEmail(person.getEmail());

        PersonEntity personToUpdate = personToPersonEntity.apply(existingPerson);

        PersonEntity updatedPerson = personRepository.save(personToUpdate);

        return personEntityToPerson.apply(updatedPerson);
    }

    @Override
    public boolean deletePerson(String id) {
        personRepository.deleteById(id);

        return true;
    }

    private String getGeneratedId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
