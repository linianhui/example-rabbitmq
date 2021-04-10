package consumer;

import java.time.OffsetDateTime;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogAdapter {

    private final String LOG_KEY = "mq:log";

    @Resource(name = "stringRedisTemplate")
    ListOperations<String, String> list;

    public void log(String format, Object... args) {
        final String message = String.format(format, args);
        log.info(message);
        list.rightPush(LOG_KEY, OffsetDateTime.now().toString() + " " + message);
    }

}
