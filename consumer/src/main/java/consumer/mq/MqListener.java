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

    @RabbitListener(queues = MqConfig.QUEUE_DIRECT)
    public void listenDirect(
        @Payload BookMessage message,
        @Headers Map headers
    ) {
        log.logReceive(null, null, MqConfig.QUEUE_DIRECT, message, headers);
        bookService.handlerBookMessage(message);
    }

    @RabbitListener(queues = MqConfig.QUEUE_FANOUT)
    public void listenFanout(
        @Payload BookMessage message,
        @Headers Map headers
    ) {
        log.logReceive(MqConfig.EXCHANGE_FANOUT, null, MqConfig.QUEUE_DIRECT, message, headers);
        bookService.handlerBookMessage(message);
    }

    @RabbitListener(queues = MqConfig.QUEUE_ROUTING_INFO)
    public void listenRoutingInfo(
        @Payload BookMessage message,
        @Headers Map headers
    ) {
        log.logReceive(
            MqConfig.EXCHANGE_DIRECT_LOG,
            headers.getOrDefault("amqp_receivedRoutingKey", "").toString(),
            null,
            message,
            headers
        );
        bookService.handlerBookMessage(message);
    }

    @RabbitListener(queues = MqConfig.QUEUE_ROUTING_ERROR)
    public void listenRoutingError(
        @Payload BookMessage message,
        @Headers Map headers
    ) {
        log.logReceive(
            MqConfig.EXCHANGE_DIRECT_LOG,
            headers.getOrDefault("amqp_receivedRoutingKey", "").toString(),
            null,
            message,
            headers
        );
        bookService.handlerBookMessage(message);
    }

}
