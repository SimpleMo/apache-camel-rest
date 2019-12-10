package org.cbr.test.apachecamelrest;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.cbr.test.apachecamelrest.model.Person;
import org.cbr.test.apachecamelrest.service.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@Component
public class RestApi extends RouteBuilder {
    @Value("${apachecamelrest.api.path}")
    private String contextPath;

    @Value("${server.port}")
    private String serverPort;

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
            .post("/person")
                .description("Добавляет переданный объект")
                .type(Person.class)
                .route()
                .routeId("person-post")
                .bean(PersonService.class, "addPerson(${body})")
                .endRest()
            .delete("/person/{id}")
                .route()
                .routeId("person-delete")
                .bean(PersonService.class, "deletePerson(${header.id})")
                .endRest()
            .post("/person/to-file")
                .description("Сохраняет json  в файл")
                .consumes(MediaType.APPLICATION_JSON)
                .type(Person.class)
                .route()
                .routeId("person-to-file")
                .to("file://work?fileName=data_${date:now:ddMMyy_hhmm}.json")
                .endRest();

        from("direct:remoteService")
                .routeId("direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .transform()
                .simple("Hello ${in.body.name}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
        //TODO добавить маршрут с to - запись в файл
    }
}
