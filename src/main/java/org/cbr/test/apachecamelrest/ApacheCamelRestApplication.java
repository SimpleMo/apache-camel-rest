package org.cbr.test.apachecamelrest;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApacheCamelRestApplication {
	@Value("${apachecamelrest.api.path}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(ApacheCamelRestApplication.class, args);
	}

	protected ServletRegistrationBean servletRegistrationBean(){
		ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath + "/*");
		servlet.setName("CamelServlet");
		return servlet;
	}

}
