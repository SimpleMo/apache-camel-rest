package org.cbr.test.apachecamelrest;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.cbr.test.apachecamelrest.model.Person;
import org.cbr.test.apachecamelrest.model.Workplace;
import org.cbr.test.apachecamelrest.repository.PersonRepository;
import org.cbr.test.apachecamelrest.repository.WorkplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ApacheCamelRestApplication {
	@Value("${apachecamelrest.api.path}")
	private String contextPath;

	@Autowired
	WorkplaceRepository workplaceRepository;

	@Autowired
	PersonRepository personRepository;

	@PostConstruct
	public void addData(){
		personRepository.save((new Person()).setId(1).setFirstName("Иван").setLastName("Иванов").setMail("ivanov@mail.ru").setMobilePhone("912-123-45-67").setWorkPhone("912-765-43-21"));
		personRepository.save((new Person()).setId(2).setFirstName("Пётр").setLastName("Петров").setMail("petrov@mail.ru").setMobilePhone("912-789-10-11").setWorkPhone("912-111-09-87"));

		workplaceRepository.save((new Workplace()).setId(1).setFirstName("Иван").setLastName("Иванов").setWorkPlace("ООО Одуванчик").setAddress("ул. Ленина"));
		workplaceRepository.save((new Workplace()).setId(2).setFirstName("Пётр").setLastName("Петров").setWorkPlace("ООО Василёк").setAddress("ул. Компрос"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ApacheCamelRestApplication.class, args);
	}

	protected ServletRegistrationBean servletRegistrationBean(){
		ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath + "/*");
		servlet.setName("CamelServlet");
		return servlet;
	}

}
