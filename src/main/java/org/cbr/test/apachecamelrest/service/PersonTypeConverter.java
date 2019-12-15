package org.cbr.test.apachecamelrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverter;
import org.apache.camel.TypeConverters;
import org.cbr.test.apachecamelrest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class PersonTypeConverter extends AbstractConverter<Person> {

    @Override
    protected Class<Person> getClazz() {
        return Person.class;
    }

    @Converter
    public InputStream personToByteArray(Person person){
        return toInputStream(person);
    }

    @Converter
    public Person byteArrayToPerson(byte[] source){
        return toPOJO(source);
    }
}
