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
        log.log("direct.producer : directSend %s %s", MqConfig.DIRECT_ROUTING_KEY, message);
        amqpTemplate.convertAndSend(MqConfig.DIRECT_ROUTING_KEY, message);
    }

    public void fanoutSend(Object message) {
        log.log("fanout.producer : fanoutSend %s %s", MqConfig.FANOUT_EXCHANGE, message);
        amqpTemplate.convertAndSend(MqConfig.FANOUT_EXCHANGE, null, message);
    }

}
