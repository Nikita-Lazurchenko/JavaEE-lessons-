package com.example;


import com.example.database.repository.UserRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringStarterLessonApplication {

	public static void main(String[] args) {
		var context = new ClassPathXmlApplicationContext("application.xml");

		var repository = context.getBean("repository1", UserRepository.class);
		System.out.println(repository);
	}

}
