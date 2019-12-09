package org.cbr.test.apachecamelrest.repository;

import org.cbr.test.apachecamelrest.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
