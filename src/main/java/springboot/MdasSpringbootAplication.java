package springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@EnableRabbit
@SpringBootApplication
@ComponentScan(basePackages = {"pokedex", "trainers"})
public class MdasSpringbootAplication {

	public static void main(String[] args) {
		SpringApplication.run(MdasSpringbootAplication.class, args);
	}

}
