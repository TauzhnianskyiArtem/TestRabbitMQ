package com.example.demo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/mq")
public class MainController {
    RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> messages){

        rabbitTemplate.convertAndSend(RabbitMsgListener.MY_Exchange,messages.get("key"), messages.get("text"));
        return ResponseEntity.ok("Success");
    }
}
