package org.cbr.test.apachecamelrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.cbr.test.apachecamelrest.model.Person;
import org.cbr.test.apachecamelrest.model.PersonWithWorkplace;
import org.cbr.test.apachecamelrest.model.ToFileDto;
import org.cbr.test.apachecamelrest.model.Workplace;
import org.cbr.test.apachecamelrest.repository.PersonWithWorkplaceRepository;
import org.cbr.test.apachecamelrest.service.PersonService;
import org.cbr.test.apachecamelrest.service.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RestApi extends RouteBuilder {
    @Value("${apachecamelrest.api.path}")
    private String contextPath;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private PersonWithWorkplaceRepository personWithWorkplaceRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy_hhmm");

    @Override
    public void configure() throws Exception {
        CamelContext context = new DefaultCamelContext();
        restConfiguration()
                .contextPath(contextPath)
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Тестовый API")
                .apiProperty("api.version", "v1")
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json);
        rest("/api/")
            .post("/bean")
                .id("api-route")
                .consumes(MediaType.APPLICATION_JSON)
                .produces(MediaType.APPLICATION_JSON)
                .bindingMode(RestBindingMode.auto)
                .type(Person.class)
                .to("direct:remoteService")
            .get("/person")
                .description("Возвращает список сохранённых объектов")
                .route()
                .routeId("persons-get")
                .bean(PersonService.class, "findPersons")
                .endRest()
            .get("/person/{id}")
                .description("Возвращает объект с переданным id")
                .route()
                .routeId("person-get")
                .bean(PersonService.class, "find(${header.id})")
                .endRest()
            .post("/person")//todo добавить валидацию
                .description("Добавляет переданный объект")
                .type(Person.class)
                .route()
                .routeId("person-post")
                .validate(exchange -> {
                    Person body = exchange.getIn().getBody(Person.class);
                    boolean mobilePhoneFormat = body.getMobilePhone().matches("^([1-9]\\d{2})-(\\d{3})-(\\d{2})-(\\d{2})$");
                    boolean workPhoneFormat = body.getWorkPhone().matches("^([1-9]\\d{2})-(\\d{3})-(\\d{2})-(\\d{2})$");
                    if (!mobilePhoneFormat || !workPhoneFormat){
                        return false;
                    }

                    String[] mobilePhoneParts = body.getMobilePhone().split("-");
                    String[] workPhoneParts = body.getWorkPhone().split("-");
                    return mobilePhoneParts[0].equals(workPhoneParts[0]);
                })
                .bean(PersonService.class, "addPerson(${body})")
                .endRest()
            .delete("/person/{id}")
                .route()
                .routeId("person-delete")
                .bean(PersonService.class, "deletePerson(${header.id})")
                .endRest()
            .get("/workplace")//Вот это - телеф
                .description("Возвращает список мест работы")
                .route()
                .routeId("workplaces-get")
                .bean(WorkplaceService.class, "getWorkplaces")
                .endRest()
            .get("/workplace/{id}")
                .description("Возвращает место работы")
                .route()
                .routeId("workplace-get")
                .bean(WorkplaceService.class, "getWorkplace(${header.id})")
                .endRest()
            .post("/workplace")
                .description("Добавляет место работы")
                .type(Workplace.class)
                .route()
                .routeId("workplace-post")
                .bean(WorkplaceService.class, "addWorkplace(${body})")
                .endRest()
            .delete("/workplace/{id}")
                .description("Удаляет место работы")
                .route()
                .routeId("workplace-delete")
                .bean(WorkplaceService.class, "deleteWorkplace(${header.id})")
                .endRest()
            .post("/person/to-file")
                .description("Сохраняет json  в файл")
                .consumes(MediaType.APPLICATION_JSON)
                .type(Person.class)
                .route()
                .routeId("person-to-file")
                .validate(exchange -> {
                    Person body = exchange.getIn().getBody(Person.class);
                    boolean mobilePhoneFormat = body.getMobilePhone().matches("^([1-9]\\d{2})-(\\d{3})-(\\d{2})-(\\d{2})$");
                    boolean workPhoneFormat = body.getWorkPhone().matches("^([1-9]\\d{2})-(\\d{3})-(\\d{2})-(\\d{2})$");
                    if (!mobilePhoneFormat || !workPhoneFormat){
                        return false;
                    }

                    String[] mobilePhoneParts = body.getMobilePhone().split("-");
                    String[] workPhoneParts = body.getWorkPhone().split("-");
                    return mobilePhoneParts[0].equals(workPhoneParts[0]);
                })
                .process(exchange -> {
                    Person body = exchange.getIn().getBody(Person.class);
                    ToFileDto toFileDto = new ToFileDto();
                    toFileDto.setPerson(body);
                    toFileDto.setFileName("data_" + sdf.format(new Date()) + ".json");
                    exchange.getIn().setBody(toFileDto, ToFileDto.class);
                })
                .to("file://prepare?fileName=${body.fileName}")
                .endRest()
            .post("/person/to-work")
                .description("Переносит json-файл в директорию work")
                .type(String.class)
                .route()
                .routeId("to-work-dir")
                .process(exchange -> {
                    String fileName = exchange.getIn().getBody(String.class);
                    File file = new File("./prepare/" + fileName);
                    exchange.getIn().setHeader("filename", file.getName());
                    exchange.getIn().setBody(file, File.class);
                })
                .to("file://work?fileName=${header.filename}&doneFileName=${file:name}.ready")
                .endRest();

        //EXEC
        from("file://work?doneFileName=${file:name}.ready&delete=true&initialDelay=60s&delay=5s")
                .routeId("person-from-file")
                .to("json-validator:person_schema.json")
                .process(exchange -> {
                    File camelFilePath = new File(exchange.getIn().getHeaders().get("CamelFilePath").toString());
                    Person person = mapper.readValue(camelFilePath, Person.class);
                    Workplace workplace = workplaceService.findByLastNameAndFirstName(person.getLastname(), person.getFirstname());
                    PersonWithWorkplace newBody = PersonWithWorkplace.fromPersonAndWorkPlace(person, workplace);
                    personWithWorkplaceRepository.save(newBody);
                    exchange.getIn().setBody(newBody, PersonWithWorkplace.class);
                })
                .to("file://data?fileName=${file:name}");

        from("direct:remoteService")
                .routeId("direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .transform()
                .simple("Hello ${in.body.name}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
    }
}
