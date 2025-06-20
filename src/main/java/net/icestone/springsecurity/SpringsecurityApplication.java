package net.icestone.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.ServletContextAware;

import jakarta.servlet.ServletContext;

@SpringBootApplication
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}
	
	  public void setServletContext(ServletContext servletContext) {
	    System.out.println(servletContext.getFilterRegistrations());
	  }
	
}
