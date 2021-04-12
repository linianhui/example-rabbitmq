package consumer.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    public static final String DIRECT_ROUTING_KEY = "testmq.direct";

    public static final String FANOUT_EXCHANGE = "testmq.fanout";

    public static final String FANOUT_QUEUE = "testmq.fanout.queue";

    public static final String EXCHANGE_DIRECT_LOG = "example_exchange.direct_log";

    public static final String QUEUE_ROUTING_INFO = "example_queue.routing_info";

    public static final String QUEUE_ROUTING_ERROR = "example_queue.routing_error";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue directQueue() {
        return QueueBuilder
            .durable(DIRECT_ROUTING_KEY)
            .build();
    }


    @Bean
    public Queue fanoutQueue() {
        return QueueBuilder
            .durable(FANOUT_QUEUE)
            .build();
    }

    @Bean
    Binding bindingFanoutQueue() {
        return BindingBuilder
            .bind(fanoutQueue())
            .to((FanoutExchange) ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE).build());
    }

    @Bean
    Declarables routing() {
        final Queue queueRoutingInfo = QueueBuilder.durable(QUEUE_ROUTING_INFO).build();
        final Queue queueRoutingError = QueueBuilder.durable(QUEUE_ROUTING_ERROR).build();
        final DirectExchange exchangeDirectLog = ExchangeBuilder.directExchange(EXCHANGE_DIRECT_LOG).build();

        return new Declarables(
            queueRoutingInfo,
            queueRoutingError,
            BindingBuilder.bind(queueRoutingInfo).to(exchangeDirectLog).with("info"),
            BindingBuilder.bind(queueRoutingInfo).to(exchangeDirectLog).with("warn"),
            BindingBuilder.bind(queueRoutingInfo).to(exchangeDirectLog).with("error"),
            BindingBuilder.bind(queueRoutingError).to(exchangeDirectLog).with("error")
        );
    }

}
