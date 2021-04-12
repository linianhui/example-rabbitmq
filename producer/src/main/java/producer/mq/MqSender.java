package producer.mq;

import common.LogAdapter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    LogAdapter log;

    public void directSend(Object message) {
        log.logSend(null, MqConfig.DIRECT_ROUTING_KEY, null, message);
        amqpTemplate.convertAndSend(MqConfig.DIRECT_ROUTING_KEY, message);
    }

    public void fanoutSend(Object message) {
        log.logSend(null, MqConfig.DIRECT_ROUTING_KEY, null, message);
        amqpTemplate.convertAndSend(MqConfig.FANOUT_EXCHANGE, null, message);
    }

}
