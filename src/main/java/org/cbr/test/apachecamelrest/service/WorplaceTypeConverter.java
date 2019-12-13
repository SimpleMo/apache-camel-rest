package org.cbr.test.apachecamelrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.cbr.test.apachecamelrest.model.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class WorplaceTypeConverter implements TypeConverters {

    @Autowired
    private ObjectMapper mapper;
    
    @Converter
    public InputStream worplaceToByteArray(Workplace workplace){
        try {
            byte[] bytes = mapper.writeValueAsBytes(workplace);
            InputStream is = new ByteArrayInputStream(bytes);
            return is;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Converter
    public Workplace byteArrayToWorplace(byte[] source){
        try {
            return mapper.readValue(source, Workplace.class);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
