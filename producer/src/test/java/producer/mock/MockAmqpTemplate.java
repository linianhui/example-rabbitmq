package producer.mock;

import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockAmqpTemplate {

    @Primary
    @Bean
    AmqpTemplate amqpTemplate() {
        return Mockito.mock(AmqpTemplate.class);
    }
}
