package consumer.mq;

import consumer.service.XxInput;
import consumer.service.XxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqListener {

    @Autowired
    XxService xxService;

    @RabbitListener(queues = "testmq.direct")
    public void listenDirect(XxInput xxInput) {
        log.info("listenDirect testmq.direct {}", xxInput.getName());
        xxService.handlerXx(xxInput);
    }
}
