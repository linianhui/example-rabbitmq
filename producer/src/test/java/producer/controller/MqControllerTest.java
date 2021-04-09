package producer.controller;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import producer.SpringTest;
import producer.mq.MqSender;

public class MqControllerTest extends SpringTest {
    @SpyBean
    MqSender mqSender;


    @Override
    protected void afterEachCore() {
        Mockito.reset(mqSender);
    }

    @Test
    void test_post_direct_should_return_200_ok() {
        Mockito
            .doNothing()
            .when(mqSender)
            .directSend(ArgumentMatchers.any());

        http.post()
            .uri("/mq/direct")
            .bodyValue(Maps.newHashMap("name", "lnh"))
            .exchange()
            .expectStatus().is2xxSuccessful();

        Mockito
            .verify(mqSender, Mockito.times(1))
            .directSend(ArgumentMatchers.any());
    }
}
