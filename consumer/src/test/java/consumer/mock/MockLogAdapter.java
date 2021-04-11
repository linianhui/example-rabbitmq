package consumer.mock;

import common.LogAdapter;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockLogAdapter {

    @Primary
    @Bean
    LogAdapter logAdapter() {
        return Mockito.mock(LogAdapter.class);
    }

}
