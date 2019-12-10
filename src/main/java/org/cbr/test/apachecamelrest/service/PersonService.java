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

    @PostConstruct
    protected void addToRepository(){
        personRepository.save((new Person()).setId(1).setFirstname("Иван").setLastname("Иванов").setEmail("ivanov@mail.ru").setMobilePhone("912-123-45-67").setWorkPhone("912-765-43-21"));
        personRepository.save((new Person()).setId(2).setFirstname("Пётр").setLastname("Петров").setEmail("petrov@mail.ru").setMobilePhone("912-789-10-11").setWorkPhone("912-111-09-87"));
    }

    public Iterable<Person> findPersons(){
        return personRepository.findAll();
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
