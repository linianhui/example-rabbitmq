package consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class XxService {

    public void handlerXx(Object message) {
        log.info("handlerXx {}", message);
    }

}
