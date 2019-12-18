package org.cbr.test.apachecamelrest.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Абоненты телефоенной книги. Это получаем со фронта и обрабатываем в виде файла
 */
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

    public Person setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Person setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public Person setWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public String getMobilePhone() {
        return mobilephone;
    }

    public Person setMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Person setEmail(String email) {
        this.email = email;
        return this;
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
