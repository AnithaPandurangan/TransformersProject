package Transformers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import Transformers.repository.DataLoader;

@SpringBootApplication
public class TransformersApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext context = SpringApplication.run(TransformersApplication.class, args);
		context.getBean(DataLoader.class).loadDefaultData();
	}

}
