package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RabbitMsgListener {

    public final static String MY_Exchange = "my:exchange";

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("my:queue1"),
                    exchange = @Exchange(MY_Exchange),
                    key = "my:key1"
            )
    )
    public void processFirstQueue(String message){
        log.info("My first queue: " + message );
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue("my:queue2"),
                    exchange = @Exchange(MY_Exchange),
                    key = "my:key2"
            )
    )
    public void processSecondQueue(String message){
        log.info("My second queue: " + message );
    }
}
