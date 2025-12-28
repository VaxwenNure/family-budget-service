package com.familybudget.budget.messaging.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.familybudget.budget.messaging.config.DomainEventsRabbitConfig.DOMAIN_EVENTS_EXCHANGE;

@Service
@RequiredArgsConstructor
public class DomainEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(String routingKey, Object event) {
        rabbitTemplate.convertAndSend(DOMAIN_EVENTS_EXCHANGE, routingKey, event);
    }
}
