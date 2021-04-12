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
        log.logSend(null, MqConfig.QUEUE_DIRECT, null, message);
        amqpTemplate.convertAndSend(MqConfig.QUEUE_DIRECT, message);
    }

    public void fanoutSend(Object message) {
        log.logSend(null, MqConfig.QUEUE_DIRECT, null, message);
        amqpTemplate.convertAndSend(MqConfig.EXCHANGE_FANOUT, null, message);
    }

    public void logRouting(String routingKey, Object message) {
        log.logSend(MqConfig.EXCHANGE_DIRECT_LOG, routingKey, null, message);
        amqpTemplate.convertAndSend(MqConfig.EXCHANGE_DIRECT_LOG, routingKey, message);
    }

}
