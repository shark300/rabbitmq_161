package com.github.message.saver.binding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageBinder {

  private static final String INCOMING_QUEUE_SPEL =
      "${service.integration.binding.incoming.queue-name}";

  private final Logger logger = LoggerFactory.getLogger(MessageBinder.class);

  @RabbitListener(queues = INCOMING_QUEUE_SPEL)
  public void receive(String message) {
    logger.info("Logging incoming message: {}", message);
  }
}
