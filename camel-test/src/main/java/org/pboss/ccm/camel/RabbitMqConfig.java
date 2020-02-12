package org.pboss.ccm.camel;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMqConfig {

  private static final String EXCHANGE_NAME = "pboss-ccm-exchange";
  private static final String QUEUE_NAME = "pboss.ccm.master";
  private static final String BINDING_KEY = "pboss.ccm.master";
  
  @Bean
  Queue queue() {
      return new Queue(QUEUE_NAME, true);
  }

  @Bean
  TopicExchange exchange() {
      return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("pboss.ccm.master");
  }
  
  @Bean
  public ConnectionFactory rabbitConnectionFactory2() {
      ConnectionFactory connectionFactory = new ConnectionFactory();
      connectionFactory.setHost("localhost");
      connectionFactory.setPort(5672);
      connectionFactory.setUsername("guest");
      connectionFactory.setPassword("guest");

      return connectionFactory;
  }
  
}
