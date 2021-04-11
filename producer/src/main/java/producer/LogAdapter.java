package producer;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogAdapter {

    private final String LOG_KEY = "mq:log";

    @Autowired
    StringRedisTemplate redisTemplate;

    public void log(String format, Object... args) {
        final String message = String.format(format, args);
        final String hostName = System.getenv().get("HOSTNAME");
        log.info(message);
        redisTemplate
            .opsForList()
            .rightPush(LOG_KEY, hostName + " " + OffsetDateTime.now() + " " + message);
    }

    public List<String> listLog() {
        log.info("listLog {} 0 -1", LOG_KEY);
        return redisTemplate
            .opsForList()
            .range(LOG_KEY, 0, -1);
    }

    public void clear() {
        redisTemplate.delete(LOG_KEY);
    }

}
