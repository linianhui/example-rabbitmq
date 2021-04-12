package consumer.mq;

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

    public static final String QUEUE_DIRECT = "example_queue.direct";

    public static final String EXCHANGE_FANOUT = "example_exchange.fanout";

    public static final String QUEUE_FANOUT = "example_queue.fanout";

    public static final String EXCHANGE_DIRECT_LOG = "example_exchange.direct_log";

    public static final String QUEUE_ROUTING_INFO = "example_queue.routing_info";

    public static final String QUEUE_ROUTING_ERROR = "example_queue.routing_error";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Declarables direct() {
        return new Declarables(
            QueueBuilder.durable(QUEUE_DIRECT).build()
        );
    }

    @Bean
    Declarables fanout() {
        final Queue queueFanout = QueueBuilder.durable(QUEUE_FANOUT).build();
        final FanoutExchange exchangeFanout = ExchangeBuilder.fanoutExchange(EXCHANGE_FANOUT).build();
        return new Declarables(
            queueFanout,
            BindingBuilder.bind(queueFanout).to(exchangeFanout)
        );
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
