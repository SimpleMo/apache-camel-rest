package org.cbr.test.apachecamelrest.service;

import org.cbr.test.apachecamelrest.model.Person;
import org.cbr.test.apachecamelrest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private List<Person> persons;

    @Autowired
    private PersonRepository personRepository;

    protected void addPersons(){
        persons.add((new Person()).setId(1).setName("Иван"));
        persons.add((new Person()).setId(2).setName("Пётр"));
    }

    public Iterable<Person> findPersons(){
        return persons;
    }

    public Person find(Integer id){
        if(id == null){
            return null;
        }
        Optional<Person> result = personRepository.findById(id);
        return result.orElse(null);
    }

    public void addPerson(Person person){
        personRepository.save(person);
    }

    public void deletePerson(Integer id){
        Person person = find(id);
        if(person == null){
            return;
        }
        personRepository.delete(person);
    }

}
