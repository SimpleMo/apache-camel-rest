package org.cbr.test.apachecamelrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.cbr.test.apachecamelrest.model.Workplace;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractConverter<T> implements TypeConverters {
    @Autowired
    private ObjectMapper mapper;

    protected abstract Class<T> getClazz();

    protected ObjectMapper getMapper() {
        return mapper;
    }

    @Converter
    public InputStream toInputStream(T entityToConvert){
        try {
            byte[] bytes = mapper.writeValueAsBytes(entityToConvert);
            return new ByteArrayInputStream(bytes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Converter
    public T toPOJO(byte[] source){
        try {
            return mapper.readValue(source, getClazz());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
