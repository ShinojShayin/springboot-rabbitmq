package com.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "ljmu_research_test_queue";
    public static final String EXCHANGE = "ljmu_research_test_exchange";
    public static final String ROUTING_KEY = "ljmu_research_test_routingKey";

    @Bean
    public Queue queue() {

        // String name, boolean durable, boolean exclusive, boolean autoDelete,
        //			@Nullable Map<String, Object> arguments
        Map<String, Object> args = new HashMap<>();
        args.put("x-queue-type", "classic");
        return new Queue(QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
