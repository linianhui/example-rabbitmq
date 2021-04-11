package common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
public class LogAutoConfig {

    @Bean
    public LogAdapter logAdapter(StringRedisTemplate stringRedisTemplate){
        return new LogAdapter(stringRedisTemplate);
    }
}
