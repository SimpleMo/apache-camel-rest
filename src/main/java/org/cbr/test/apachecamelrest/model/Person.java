package org.cbr.test.apachecamelrest.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "WORK_PHONE")
    private String workphone;

    @Column(name = "MOBILE_PHONE")
    private String mobilephone;

    @Column(name = "EMAIL")
    private String email;

    public Integer getId() {
        return id;
    }

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public void setWorkPhone(String workphone) {
        this.workphone = workphone;
    }

    public String getMobilePhone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person setName(String name) {
        this.firstname = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) &&
                firstname.equals(person.firstname) &&
                lastname.equals(person.lastname) &&
                workphone.equals(person.workphone) &&
                mobilephone.equals(person.mobilephone) &&
                email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, workphone, mobilephone, email);
    }
}
