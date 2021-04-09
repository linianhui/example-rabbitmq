package producer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void directSend(Object message) {
        log.info("directSend testmq.direct {}", message);
        amqpTemplate.convertAndSend("testmq.direct", message);
    }
}
