package pl.amilosh.rabbit_mq_sample;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queue() {
        return new Queue("myQueue");
    }

    @Bean
    public Queue queue1() {
        return new Queue("myQueue1");
    }

    @Bean
    public Queue queue2() {
        return new Queue("myQueue2");
    }

    // Send the same message for several listener who listen several queues. Each queue bind this exchange.
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("common-exchange");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    // Direct exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("directMyQueue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("directMyQueue2");
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("error");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("warning");
    }

    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("info");
    }

    // First way to implement listener
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("myQueue");
        container.setMessageListener(message -> {
            System.out.println();
            System.out.println("---- message from RabbitMQ ----");
            System.out.println();

            System.out.println(message);

            System.out.println();
            System.out.println("---- message from RabbitMQ ----");
            System.out.println();
        });
        return container;
    }
}
