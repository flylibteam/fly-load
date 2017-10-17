package org.flylib.load;

import org.flylib.load.model.Person;
import org.flylib.load.spring.AppContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
		/**
		 * Usage 1 Get ApplicationContext
		 */
		ApplicationContext appContext = AppContextHolder.getApplicationContext();
		Person person = appContext.getBean("person", Person.class);
		/**
		 * Usage 2 Get ApplicationContext
		 * <code>Person person = AppContextHolder.getBean("person", Person.class);</code>
		 */
		
		System.out.println(person.getName());
	}
}