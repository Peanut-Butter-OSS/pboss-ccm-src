package org.pboss.ccm.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

  // // Works, but this creates a direct exchange
  // @Override
  // public void configure() throws Exception {
  // from("timer:hello?period=1000")
  // .transform(simple("Random number ${random(0,100)}"))
  // .to("rabbitmq:foo");
  //
  // from("rabbitmq:foo?connectionFactory=rabbitConnectionFactory")
  // .log("From RabbitMQ: ${body}");
  // }

//  @Override
//  public void configure() throws Exception {
//    from("timer:hello?period=1000").transform(simple("Random number ${random(0,100)}"))
//        .to("rabbitmq:foo?connectionFactory=rabbitConnectionFactory2");
//
//    from("rabbitmq:foo?connectionFactory=rabbitConnectionFactory2&threadPoolSize=1&autoAck=false")
//        .log("From RabbitMQ: ${body}");
//  }


   @Override
   public void configure() {
  

   from("rabbitmq:pboss-ccm-exchange?exchangeType=topic&autoDelete=false&routingKey=pboss.ccm.master")
   .routeId("RabbiqMqConsumer")
   .log("Message received: ${body}");
  
   //from("rabbitmq:pboss-ccm-exchange?routingKey=pboss.ccm.master")
   //.routeId("RabbiqMqConsumer")
   //.log("Message received: ${body}");
  
   //from("rabbitmq:pboss-ccm-exchange?connectionFactory=#rabbitConnectionFactory&routingKey=pboss.ccm.master")
   //.to("log:cheese");
   }

}
