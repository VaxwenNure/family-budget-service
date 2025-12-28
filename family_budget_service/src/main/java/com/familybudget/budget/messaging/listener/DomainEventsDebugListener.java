package com.familybudget.budget.messaging.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.familybudget.budget.messaging.config.DomainEventsRabbitConfig.DEBUG_QUEUE;

@Component
public class DomainEventsDebugListener {

    @RabbitListener(queues = DEBUG_QUEUE)
    public void onAnyEvent(Message message) {
        String json = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println(">>> DOMAIN EVENT RECEIVED (debug JSON): " + json);
        System.out.println(">>> routingKey=" + message.getMessageProperties().getReceivedRoutingKey());
    }
}
