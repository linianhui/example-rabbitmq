package consumer.mq;

import common.LogAdapter;
import consumer.service.XxService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqListener {

    @Autowired
    XxService xxService;

    @Autowired
    LogAdapter log;

    @RabbitListener(queues = MqConfig.DIRECT_ROUTING_KEY)
    public void listenDirect(Object message) {
        log.log("direct.consumer : listenDirect %s %s", MqConfig.DIRECT_ROUTING_KEY, message);
        xxService.handlerXx(message);
    }

    @RabbitListener(queues = MqConfig.FANOUT_QUEUE)
    public void listenFanout(Object message) {
        log.log("fanout.consumer : listenFanout %s %s", MqConfig.FANOUT_QUEUE, message);
        xxService.handlerXx(message);
    }

}
