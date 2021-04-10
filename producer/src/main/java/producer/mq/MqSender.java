package producer.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import producer.LogAdapter;

@Component
public class MqSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    LogAdapter log;

    public void directSend(Object message) {
        log.log("direct.producer : directSend testmq.direct %s", message);
        amqpTemplate.convertAndSend("testmq.direct", message);
    }

}
