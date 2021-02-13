package it.spaziowiki.fatturazione;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.spaziowiki.fatturazione")	
//public class FatturazioneApplication {
//Qui sotto per tomcat
public class FatturazioneApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FatturazioneApplication.class, args);
	}

	//Qui sotto per tomcat
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FatturazioneApplication.class);
    }

}
