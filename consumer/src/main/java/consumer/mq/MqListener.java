package consumer.mq;

import consumer.LogAdapter;
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

    @RabbitListener(queues = "testmq.direct")
    public void listenDirect(Object message) {
        log.log("direct.consumer : listenDirect testmq.direct %s", message);
        xxService.handlerXx(message);
    }

}
