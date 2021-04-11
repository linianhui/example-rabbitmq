package producer.mock;

import common.LogAdapter;
import common.LogAutoConfig;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import({LogAutoConfig.class})
public class MockLogAdapter {

    @Primary
    @Bean
    LogAdapter logAdapter() {
        return Mockito.mock(LogAdapter.class);
    }

}
