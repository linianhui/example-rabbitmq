package producer.mq;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import producer.SpringTest;

public class MqSenderTest extends SpringTest {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    MqSender mqSender;

    @Override
    protected void afterEachCore() {
        Mockito.reset(amqpTemplate);
    }

    @Test
    void test_directSend_should_ok() {
        final String message = "test-message";
        mqSender.directSend(message);

        Mockito
            .verify(amqpTemplate, Mockito.times(1))
            .convertAndSend(
                ArgumentMatchers.eq(MqConfig.QUEUE_DIRECT),
                ArgumentMatchers.same(message)
            );
    }
}
