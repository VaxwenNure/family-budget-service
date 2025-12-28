package com.familybudget.budget.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainEventsRabbitConfig {

    public static final String DOMAIN_EVENTS_EXCHANGE = "domain.events";
    public static final String DEBUG_QUEUE = "budget.domain.events.debug";

    @Bean
    public TopicExchange domainEventsExchange() {
        return new TopicExchange(DOMAIN_EVENTS_EXCHANGE, true, false);
    }

    @Bean
    public Queue debugQueue() {
        return QueueBuilder.durable(DEBUG_QUEUE).build();
    }

    @Bean
    public Binding bindAllToDebugQueue(Queue debugQueue, TopicExchange domainEventsExchange) {
        // "#" = receive ALL routing keys
        return BindingBuilder.bind(debugQueue).to(domainEventsExchange).with("#");
    }
}
