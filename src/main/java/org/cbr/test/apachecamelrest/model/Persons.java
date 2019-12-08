package org.cbr.test.apachecamelrest.model;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Repository
public class Persons extends ArrayList<Person> {

    @PostConstruct
    protected void addPersons(){
        add((new Person()).setId(1).setName("Иван"));
        add((new Person()).setId(2).setName("Пётр"));
    }
}
