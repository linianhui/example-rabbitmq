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

    public static final String JUST_FOR_INIT_AUTO_DECLARATION = "just_for_init_auto_declaration";

    public static final String DIRECT_ROUTING_KEY = "testmq.direct";

    public static final String FANOUT_EXCHANGE = "testmq.fanout";

    public static final String EXCHANGE_DIRECT_LOG = "example_exchange.direct_log";


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder
            .fanoutExchange(FANOUT_EXCHANGE)
            .durable(true)
            .build();
    }

    @Bean
    public Exchange directLogExchange() {
        return ExchangeBuilder
            .directExchange(EXCHANGE_DIRECT_LOG)
            .durable(true)
            .build();
    }

    @Bean
    public Queue just_for_init_auto_declaration() {
        return QueueBuilder
            .durable(JUST_FOR_INIT_AUTO_DECLARATION)
            .build();
    }

}
