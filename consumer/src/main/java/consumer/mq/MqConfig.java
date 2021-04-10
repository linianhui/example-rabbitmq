package consumer.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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

}
