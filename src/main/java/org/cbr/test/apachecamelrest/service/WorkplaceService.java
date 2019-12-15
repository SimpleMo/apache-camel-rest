package org.cbr.test.apachecamelrest.service;

import org.cbr.test.apachecamelrest.model.Person;
import org.cbr.test.apachecamelrest.model.Workplace;
import org.cbr.test.apachecamelrest.repository.WorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class WorkplaceService {

    @Autowired
    private WorkplaceRepository workplaceRepository;

    public Iterable<Workplace> getWorkplaces() {
        return workplaceRepository.findAll();
    }

    public Workplace getWorkplace(Integer id){
        Optional<Workplace> byId = workplaceRepository.findById(id);
        return byId.orElse(null);
    }

    public void addWorkplace(Workplace workplace){
        workplaceRepository.save(workplace);
    }

    public void deleteWorkplace(Integer id){
        Optional<Workplace> byId = workplaceRepository.findById(id);
        if(!byId.isPresent()){
            return;
        }
        workplaceRepository.delete(byId.get());
    }

    public Workplace findByLastNameAndFirstName(String lastName, String firstName){
        return workplaceRepository.findByLastNameAndFirstName(lastName, firstName);
    }
}
