package producer.mq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    public static final String DIRECT_ROUTING_KEY = "testmq.direct";

    public static final String FANOUT_EXCHANGE = "testmq.fanout";

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

}
