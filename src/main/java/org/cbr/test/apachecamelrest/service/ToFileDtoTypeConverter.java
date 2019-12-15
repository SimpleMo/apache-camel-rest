package org.cbr.test.apachecamelrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Converter;
import org.cbr.test.apachecamelrest.model.ToFileDto;
import org.cbr.test.apachecamelrest.model.Workplace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class ToFileDtoTypeConverter extends AbstractConverter<ToFileDto> {

    @Converter
    @Override
    public InputStream toInputStream(ToFileDto entityToConvert) {
        try {
            byte[] bytes = getMapper().writeValueAsBytes(entityToConvert.getPerson());
            return new ByteArrayInputStream(bytes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Class<ToFileDto> getClazz() {
        return ToFileDto.class;
    }
}
