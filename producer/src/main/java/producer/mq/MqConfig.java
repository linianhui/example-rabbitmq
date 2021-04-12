package producer.mq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    public static final String QUEUE_JUST_FOR_INIT_AUTO_DECLARATION = "example_queue.just_for_init_auto_declaration";

    public static final String QUEUE_DIRECT = "example_queue.direct";

    public static final String EXCHANGE_FANOUT = "example_exchange.fanout";

    public static final String EXCHANGE_DIRECT_LOG = "example_exchange.direct_log";


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Exchange exchangeFanout() {
        return ExchangeBuilder
            .fanoutExchange(EXCHANGE_FANOUT)
            .durable(true)
            .build();
    }

    @Bean
    public Exchange exchangeDirectLog() {
        return ExchangeBuilder
            .directExchange(EXCHANGE_DIRECT_LOG)
            .durable(true)
            .build();
    }

    @Bean
    public Queue queueJustForInitAutoDeclaration() {
        return QueueBuilder
            .durable(QUEUE_JUST_FOR_INIT_AUTO_DECLARATION)
            .build();
    }

}
