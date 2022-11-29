package com.rabbitmq.demo;

import com.rabbitmq.demo.config.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class SpringbootRabbitmqExampleApplication {


	public static void main(String[] args) throws IOException {

		SpringApplication.run(SpringbootRabbitmqExampleApplication.class, args);

	}

}
