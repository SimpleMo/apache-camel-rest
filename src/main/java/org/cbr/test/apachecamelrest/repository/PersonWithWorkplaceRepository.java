package org.cbr.test.apachecamelrest.repository;

import org.cbr.test.apachecamelrest.model.PersonWithWorkplace;
import org.springframework.data.repository.CrudRepository;

public interface PersonWithWorkplaceRepository extends CrudRepository<PersonWithWorkplace, Integer> {
}
