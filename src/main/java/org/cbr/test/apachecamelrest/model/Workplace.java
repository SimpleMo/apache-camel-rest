package org.cbr.test.apachecamelrest.model;

import javax.persistence.*;

/**
 * Справочник места работы. Это заполняем и просматриваем с формы
 */
@Entity
public class Workplace {
    //Места работы. Это заполняем и просматриваем с формы
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "WORK_PLACE")
    private String workPlace;

    @Column(name = "ADDRESS")
    private String address;

    public Integer getId() {
        return id;
    }

    public Workplace setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Workplace setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Workplace setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public Workplace setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Workplace setAddress(String address) {
        this.address = address;
        return this;
    }
}
