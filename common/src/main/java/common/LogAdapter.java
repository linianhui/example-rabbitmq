package common;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
public class LogAdapter {

    private final String LOG_KEY = "mq:log";

    private final StringRedisTemplate redisTemplate;

    public LogAdapter(final StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void log(String format, Object... args) {
        final String arg = String.format(format, args);
        final String hostName = System.getenv().get("HOSTNAME");
        final OffsetDateTime time = OffsetDateTime.now(ZoneOffset.ofHours(8));
        final String value = hostName + " " + time + " " + arg;
        log.info(value);
        redisTemplate.opsForList().rightPush(LOG_KEY, value);
    }

    public void logSend(
        String exchange,
        String routingKey,
        String queue,
        Object payload
    ) {
        log("send exchange=%s routingKey=%s queue=%s payload=%s",
            exchange, routingKey, queue, payload
        );
    }

    public void logReceive(
        String exchange,
        String routingKey,
        String queue,
        Object headers,
        Object payload
    ) {
        log("receive exchange=%s routingKey=%s queue=%s payload=%s headers=%s",
            exchange, routingKey, queue, headers, payload
        );
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
