package org.houseone.person.controller;

import lombok.AllArgsConstructor;
import org.houseone.person.model.Person;
import org.houseone.person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") String id) {
        Person person = personService.getPersonById(id);

        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> personList = personService.getAllPersons();

        if (CollectionUtils.isEmpty(personList)) {
            return new ResponseEntity<>(personList, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @PostMapping(value = "/persons")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person addedPerson = personService.addPerson(person);

        return new ResponseEntity<>(addedPerson, HttpStatus.OK);
    }

    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
        Person existingPerson = personService.getPersonById(id);

        if (existingPerson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Person updatedPerson = personService.updatePerson(id, person);

        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        Person person = personService.getPersonById(id);

        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personService.deletePerson(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
