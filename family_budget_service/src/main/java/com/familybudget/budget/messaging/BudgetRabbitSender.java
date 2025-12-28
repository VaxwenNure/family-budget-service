package com.familybudget.budget.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
@RequiredArgsConstructor
public class BudgetRabbitSender {

    public static final String BUDGET_QUEUE = "budget_queue";

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public String send(@RequestParam String message) {
        rabbitTemplate.convertAndSend(BUDGET_QUEUE, message);
        return "Sent to RabbitMQ queue '" + BUDGET_QUEUE + "': " + message;
    }
}
