package pl.amilosh.rabbit_mq_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class RabbitMqSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqSampleApplication.class, args);
	}
}
