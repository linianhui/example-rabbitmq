package consumer.mq;

import java.util.Map;

import common.LogAdapter;
import consumer.service.BookMessage;
import consumer.service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MqListener {

    @Autowired
    BookService bookService;

    @Autowired
    LogAdapter log;

    @RabbitListener(queues = MqConfig.DIRECT_ROUTING_KEY)
    public void listenDirect(
        @Payload BookMessage message,
        @Headers Map headers
    ) {
        log.logReceive(null, null, MqConfig.DIRECT_ROUTING_KEY, headers, message);
        bookService.handlerBookMessage(message);
    }

    @RabbitListener(queues = MqConfig.FANOUT_QUEUE)
    public void listenFanout(
        @Payload BookMessage message,
        @Headers Map headers
    ) {
        log.logReceive(MqConfig.FANOUT_EXCHANGE, null, MqConfig.DIRECT_ROUTING_KEY, headers, message);
        bookService.handlerBookMessage(message);
    }

}
