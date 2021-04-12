package producer.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producer.mq.MqSender;

@RestController
@RequestMapping(path = "mq")
public class MqController {

    @Autowired
    MqSender mqSender;

    @PostMapping(path = "direct")
    public void direct(
        @RequestBody Map message
    ) {
        message.put("uuid", UUID.randomUUID().toString());
        mqSender.directSend(message);
    }

    @PostMapping(path = "fanout")
    public void fanout(
        @RequestBody Map message
    ) {
        message.put("uuid", UUID.randomUUID().toString());
        mqSender.fanoutSend(message);
    }

    @PostMapping(path = "routing")
    public void routing(
        @RequestBody Map message
    ) {
        message.put("uuid", UUID.randomUUID().toString());
        mqSender.logRouting("info", message);

        message.put("uuid", UUID.randomUUID().toString());
        mqSender.logRouting("warn", message);

        message.put("uuid", UUID.randomUUID().toString());
        mqSender.logRouting("error", message);
    }

}
