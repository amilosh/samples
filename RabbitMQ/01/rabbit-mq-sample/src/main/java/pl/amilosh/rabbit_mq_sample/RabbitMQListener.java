package pl.amilosh.rabbit_mq_sample;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    // Second way to implement listener
    @RabbitListener(queues = "myQueue")
    public void firstListener(String message) {
        System.out.println();
        System.out.println("---- message from RabbitMQ by RabbitListener ----");
        System.out.println();

        System.out.println(message);

        System.out.println();
        System.out.println("---- message from RabbitMQ by RabbitListener ----");
        System.out.println();
    }

    /**
     * If there are several listeners for the ssame queue, messages will be listen by random listener (free listener)
     */
    @RabbitListener(queues = "myQueue")
    public void secondListener(String message) {
        System.out.println();
        System.out.println("---- message from RabbitMQ by RabbitListener ----");
        System.out.println();

        System.out.println(message);

        System.out.println();
        System.out.println("---- message from RabbitMQ by RabbitListener ----");
        System.out.println();
    }

    /**
     * In order to every listener recieve the same message: create exchange, bind queues to this exchange, each queue for each listener.
     */
    @RabbitListener(queues = "myQueue1")
    public void listener1(String message) {
        System.out.println();
        System.out.println("---- message from RabbitMQ by listener1 ----");
        System.out.println();

        System.out.println(message);

        System.out.println();
        System.out.println("---- message from RabbitMQ by listener1 ----");
        System.out.println();
    }

    @RabbitListener(queues = "myQueue2")
    public void listener2(String message) {
        System.out.println();
        System.out.println("---- message from RabbitMQ by listener2 ----");
        System.out.println();

        System.out.println(message);

        System.out.println();
        System.out.println("---- message from RabbitMQ by listener2 ----");
        System.out.println();
    }

    /**
     * Listener directMyQueue1 receives messages with key 'error'.
     * Listener directMyQueue2 receives messages with key 'warning', 'info'.
     */
    @RabbitListener(queues = "directMyQueue1")
    public void directListener1(String message) {
        System.out.println();
        System.out.println("---- message from RabbitMQ by directListener1 ----");
        System.out.println();

        System.out.println(message);

        System.out.println();
        System.out.println("---- message from RabbitMQ by directListener1 ----");
        System.out.println();
    }

    @RabbitListener(queues = "directMyQueue2")
    public void directListener2(String message) {
        System.out.println();
        System.out.println("---- message from RabbitMQ by directListener2 ----");
        System.out.println();

        System.out.println(message);

        System.out.println();
        System.out.println("---- message from RabbitMQ by directListener2 ----");
        System.out.println();
    }
}
