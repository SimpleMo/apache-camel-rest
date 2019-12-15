package org.cbr.test.apachecamelrest.model;

public class ToFileDto {
    private Person person;
    private String fileName;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
