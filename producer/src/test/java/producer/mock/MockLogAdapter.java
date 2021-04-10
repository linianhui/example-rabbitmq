package producer.mock;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import producer.LogAdapter;

@Configuration
public class MockLogAdapter {

    @Primary
    @Bean
    LogAdapter logAdapter() {
        return Mockito.mock(LogAdapter.class);
    }

}
