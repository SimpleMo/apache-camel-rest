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
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "WORK_PHONE")
    private String workphone;

    @Column(name = "MOBILE_PHONE")
    private String mobilephone;

    @Column(name = "EMAIL")
    private String mail;

    public Integer getId() {
        return id;
    }

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getMail() {
        return mail;
    }

    public Person setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Person setName(String name) {
        this.firstName = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                workphone.equals(person.workphone) &&
                mobilephone.equals(person.mobilephone) &&
                mail.equals(person.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, workphone, mobilephone, mail);
    }
}
