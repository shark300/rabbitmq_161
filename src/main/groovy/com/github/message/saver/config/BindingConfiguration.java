package com.github.message.saver.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConfiguration {

  @Bean
  public Queue incomingQueue(BindingConfigurationProperties bindingConfigurationProperties) {
    var incoming = bindingConfigurationProperties.getIncoming();
    var deadLetter = incoming.getDeadLetter();
    return QueueBuilder.durable(incoming.getQueueName())
        .deadLetterExchange(deadLetter.getExchangeName())
        .deadLetterRoutingKey(deadLetter.getRoutingKey())
        .build();
  }

  @Bean
  public HeadersExchange incomingExchange(
      BindingConfigurationProperties bindingConfigurationProperties) {
    return new HeadersExchange(bindingConfigurationProperties.getIncoming().getExchangeName());
  }

  @Bean
  public DirectExchange incomingDeadLetterExchange(
      BindingConfigurationProperties bindingConfigurationProperties) {
    return new DirectExchange(
        bindingConfigurationProperties.getIncoming().getDeadLetter().getExchangeName());
  }

  @Bean
  public Binding incomingRequestBinding(HeadersExchange incomingExchange, Queue incomingQueue) {
    return BindingBuilder.bind(incomingQueue).to(incomingExchange).where("type").exists();
  }
}
