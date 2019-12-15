package org.cbr.test.apachecamelrest.service;

import org.cbr.test.apachecamelrest.model.PersonWithWorkplace;
import org.springframework.stereotype.Component;

@Component
public class PersonWithWorkplaceConverter extends AbstractConverter<PersonWithWorkplace> {

    @Override
    protected Class<PersonWithWorkplace> getClazz() {
        return PersonWithWorkplace.class;
    }
}
