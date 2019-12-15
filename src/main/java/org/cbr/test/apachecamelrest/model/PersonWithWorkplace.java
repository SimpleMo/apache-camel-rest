package org.cbr.test.apachecamelrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PersonWithWorkplace extends Person {

    @Column(name = "WORK")
    private String work;

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public static PersonWithWorkplace fromPersonAndWorkPlace(Person person, Workplace workplace){
        PersonWithWorkplace result = new PersonWithWorkplace();
        result.setId(person.getId());
        result.setFirstname(person.getFirstname());
        result.setLastname(person.getLastname());
        result.setEmail(person.getEmail());
        result.setWorkPhone(person.getWorkPhone());
        result.setMobilePhone(person.getMobilePhone());
        result.setWork(workplace.getWorkPlace() + " " + workplace.getAddress());

        return result;
    }
}
