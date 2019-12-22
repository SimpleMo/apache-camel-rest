package org.cbr.test.apachecamelrest.model;

import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Справочник Телефонная книга, его на фронте только просматриваем
 */
@Entity
public class PersonWithWorkplace extends Person {
    @Column(name = "WORK")
    private String work;

    @NonNull
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public static PersonWithWorkplace fromPersonAndWorkPlace(Person person, Workplace workplace){
        PersonWithWorkplace result = new PersonWithWorkplace();
        result.setId(person.getId());
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setMail(person.getMail());
        result.setWorkPhone(person.getWorkPhone());
        result.setMobilePhone(person.getMobilePhone());
        result.setWork(workplace.getWorkPlace() + " " + workplace.getAddress());

        return result;
    }
}
