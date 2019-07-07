package org.houseone.person.service;

import org.houseone.person.entity.PersonEntity;
import org.houseone.person.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    Person getPersonById(String id);

    List<Person> getAllPersons();

    Person addPerson(Person person);

    Person updatePerson(String id, Person person);

    boolean deletePerson(String id);
}
